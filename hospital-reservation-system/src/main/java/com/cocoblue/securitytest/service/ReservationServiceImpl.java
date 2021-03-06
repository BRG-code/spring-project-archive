package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.ReservationDao;
import com.cocoblue.securitytest.dto.AvailableDateDto;
import com.cocoblue.securitytest.dto.Doctor;
import com.cocoblue.securitytest.dto.Holiday;
import com.cocoblue.securitytest.dto.Reservation;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ReservationServiceImpl implements  ReservationService{
    private final ReservationDao reservationDao;
    private final HolidayService holidayService;

    public ReservationServiceImpl(ReservationDao reservationDao, HolidayService holidayService) {
        this.reservationDao = reservationDao;
        this.holidayService = holidayService;
    }

    @Override
    public List<Reservation> getAllConfirmedReservation() {
        return reservationDao.getAllConfirmedReservation();
    }

    @Override
    public List<Reservation> getAllConfirmedReservationByDoctorNo(long doctorNo, LocalDateTime localDateTime) {
        return reservationDao.getAllConfirmedReservationByDoctorNo(doctorNo, localDateTime);
    }

    @Override
    public Boolean makeReservation(Reservation reservation) {
        return reservationDao.makeReservation(reservation);
    }

    @Override
    public Boolean cancelReservation(long rno) {
        return reservationDao.cancelReservation(rno);
    }

    @Override
    public List<AvailableDateDto> getAvailableDate() {
        List<Holiday> holidayList = getRecentHoliday();
        List<AvailableDateDto> availableDateList = new ArrayList<>();

        for(int i = 1; i < 8; i++) {
            LocalDate plusDate = LocalDate.now().plusDays(i);

            if(!holidayService.judgeHoliday(holidayList, plusDate)) {
                availableDateList.add(new AvailableDateDto(plusDate));
            }
        }

        return availableDateList;
    }

    private List<Holiday> getRecentHoliday() {
        // ?????? ???????????? ?????????.
        List<Holiday> holidayList = holidayService.getHolidaysUntilSevenDaysLater();

        // ????????? ?????? ?????? ????????? ????????? ???????????? ???????????? ????????? ????????????, ????????????????????? NIA??? ????????? ????????? ??????.
        for(Holiday holiday : holidayList) {
            if(!holiday.getCustomDate() && holiday.getRegTime().plusDays(1).isBefore(LocalDateTime.now())) {
                HolidayDbUpdateThread hdt = new HolidayDbUpdateThread(holidayService);
                Thread t = new Thread(hdt,"HolidayDbUpdateThread");
                t.start();
                break;
            }
        }

        return holidayList;
    }

    @Override
    public List<String> configureAvailableTime(String dateString, Doctor doctor) {
        List<String> result;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDate.parse(dateString, formatter).atStartOfDay();

        // ????????? ????????????, ????????? ???????????? ??????
        if(date.getDayOfWeek().getValue() != 6) {
            result = new ArrayList<>(Arrays.asList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
                                        "14:00", "14:30", "15:00", "15:30", "16:00", "17:00", "17:30"));

        } else {
            result = new ArrayList<>(Arrays.asList("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"));
        }

        // ?????????????????? ???????????? ????????? LIST Element ??????
        Iterator<String> iter = result.iterator();
        while (iter.hasNext()) {
            String time = iter.next();
            for(Reservation reservation : reservationDao.getAllConfirmedReservationByDoctorNo(doctor.getDoctorNo(), date)) {
                if(reservation.getReservationTime().toLocalTime().toString().equals(time)) {
                    iter.remove();
                    // ?????? ???????????? ??? ?????? ???????????? ?????? ????????????, Reservation ?????? for??? ?????? ??????
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public long getReservationCount(long cno){
        return reservationDao.getReservationCount(cno);
    }
}
