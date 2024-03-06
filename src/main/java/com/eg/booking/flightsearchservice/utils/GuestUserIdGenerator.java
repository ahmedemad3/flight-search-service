package com.eg.booking.flightsearchservice.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestUserIdGenerator {

	public static String generateGuestUserId(HttpServletRequest request , HttpServletResponse response) {
		Optional<Cookie> guestIdCookie = getGuestIdCookie(request);

		if (guestIdCookie.isPresent()) {
			return guestIdCookie.get().getValue();
		} else {
			String newGuestId = generateUniqueGuestId();
			setGuestIdCookie(newGuestId , response);
			return newGuestId;
		}
	}

	private static Optional<Cookie> getGuestIdCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			return Arrays.stream(cookies).filter(cookie -> "guestUserId".equals(cookie.getName())).findFirst();
		}
		return Optional.empty();
	}

	private static void setGuestIdCookie(String guestId, HttpServletResponse response) {
		// Set a cookie with the guestUserId on the client side
		// Note: This is a simplified example, and you might want to add more cookie
		// attributes
		// such as domain, path, expiration, etc.
		Cookie guestIdCookie = new Cookie("guestUserId", guestId);
//		guestIdCookie.setHttpOnly(false);
		response.addCookie(guestIdCookie);
		// Add more cookie attributes as needed
	}

	private static String generateUniqueGuestId() {
		// Generate a unique identifier using a more robust approach (e.g., UUID)
		return "GUEST_" + UUID.randomUUID().toString();
	}
}
