package net.thumbtack.school.buscompany.controller;

import net.thumbtack.school.buscompany.cookie.BusCompanyCookies;
import net.thumbtack.school.buscompany.dto.request.admin.trip.RegisterTripDtoRequest;
import net.thumbtack.school.buscompany.dto.request.admin.trip.UpdateTripDtoRequest;
import net.thumbtack.school.buscompany.dto.response.admin.*;
import net.thumbtack.school.buscompany.dto.response.common.trip.GetTripsDtoResponse;
import net.thumbtack.school.buscompany.exception.BusCompanyException;
import net.thumbtack.school.buscompany.service.AdminService;
import net.thumbtack.school.buscompany.service.UserService;
import net.thumbtack.school.buscompany.validation.Date;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    private final UserService userService;
    private final AdminService adminService;

    public TripController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RegisterTripDtoResponse addTrip(
            @CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
            @RequestBody @Valid RegisterTripDtoRequest request){
        return adminService.addTrip(cookieValue, request);
    }

    @PutMapping(value = "/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UpdateTripDtoResponse updateTrip(@CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
                                                            @PathVariable("tripId") int tripId,
                                                            @RequestBody UpdateTripDtoRequest request) throws BusCompanyException {
        return adminService.updateTrip(cookieValue, tripId, request);
    }


    @DeleteMapping(value = "/{tripId}")
    public DeleteTripDtoResponse deleteTrip(@CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
                                                            @PathVariable("tripId") int tripId) throws BusCompanyException {
        return adminService.deleteTrip(cookieValue, tripId);
    }

    @GetMapping(value = "/{tripId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetTripProfileDtoResponse getTripInfo(@CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
                                                 @PathVariable("tripId") int tripId) {
        return adminService.getTripInfo(cookieValue, tripId);
    }

    @PostMapping(value = "/{tripId}/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApproveTripDtoResponse approveTrip(@CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
                                              @PathVariable("tripId") int tripId) {
        return adminService.approveTrip(cookieValue, tripId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public GetTripsDtoResponse getAllTrips(@CookieValue(required = false, name = BusCompanyCookies.JAVASESSIONID) String cookieValue,
                                           @RequestParam(required = false) String fromStation,
                                           @RequestParam(required = false) String toStation,
                                           @RequestParam(required = false) String busName,
                                           @RequestParam(required = false) @Date(style = "yyyy-MM-dd") @Valid String fromDate,
                                           @RequestParam(required = false) @Date(style = "yyyy-MM-dd") @Valid String toDate) throws ParseException {
        return userService.getAllTrips(cookieValue, fromStation, toStation, busName, fromDate, toDate);
    }

}
