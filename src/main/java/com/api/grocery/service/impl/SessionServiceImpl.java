
package com.api.grocery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.api.grocery.models.Order;
import com.api.grocery.service.SessionService;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionServiceImpl implements SessionService {

	@Override
	public HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);
	}

	@Override
    public void setCurrentListOrder(List<Order> currentListOrder) {
        getSession().setAttribute("currentListOrder", currentListOrder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getCurrentListOrder() {
    	List<Order> currentListOrder = (List<Order>) getSession().getAttribute("currentListOrder");
        return currentListOrder != null ? currentListOrder : new ArrayList<>();
    }
}
