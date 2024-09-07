package com.api.grocery.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.api.grocery.enums.OrderStateEnum;

public class OrderStateEnumTypeHandler implements TypeHandler<OrderStateEnum>{
	@Override
	public void setParameter(PreparedStatement ps, int i, OrderStateEnum parameter, JdbcType jdbcType) throws SQLException {

		if (parameter!=null)
			ps.setString(i, (parameter).getValue());
        else
            ps.setNull(i, JdbcType.VARCHAR.TYPE_CODE);

	}

	@Override
	public OrderStateEnum getResult(ResultSet rs, String columnName)
			throws SQLException {
		String orderStateStr = rs.getString(columnName);
		for (OrderStateEnum orderState : OrderStateEnum.values()) {
			if (orderState.getValue().equals(orderStateStr))
				return orderState;
		}
		return null;
	}

	@Override
	public OrderStateEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String orderStateStr = cs.getString(columnIndex);
		for (OrderStateEnum orderState : OrderStateEnum.values()) {
			if (orderState.getValue().equals(orderStateStr))
				return orderState;
		}
		return null;
	}

	@Override
	public OrderStateEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
		String orderStateStr = rs.getString(columnIndex);
		for (OrderStateEnum orderState : OrderStateEnum.values()) {
			if (orderState.getValue().equals(orderStateStr))
				return orderState;
		}
		return null;
	}
}
