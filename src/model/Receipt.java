package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import database.Connect;

public class Receipt {
	private Integer receiptId;
	private Order receiptOrder;
	private Double receiptPaymentAmount;
	private Date receiptPaymentDate;
	private String receiptPaymentType;
	
	public Receipt(Integer receiptId, Order receiptOrder, Double receiptPaymentAmount, Date receiptPaymentDate,
			String receiptPaymentType) {
		super();
		this.receiptId = receiptId;
		this.receiptOrder = receiptOrder;
		this.receiptPaymentAmount = receiptPaymentAmount;
		this.receiptPaymentDate = receiptPaymentDate;
		this.receiptPaymentType = receiptPaymentType;
	}
	public Integer getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}
	public Order getReceiptOrder() {
		return receiptOrder;
	}
	public void setReceiptOrder(Order receiptOrder) {
		this.receiptOrder = receiptOrder;
	}
	public Double getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}
	public void setReceiptPaymentAmount(Double receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}
	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}
	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}
	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}
	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
	//CREATE method
	public static Integer createReceipt(Order order, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		String preparedQuery = "INSERT INTO receipt (orderId, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = Connect.getConnection().prepareStatement(preparedQuery);
		
		try {
			ps.setInt(1, order.getOrderId());
			ps.setDouble(2, receiptPaymentAmount);
			ps.setDate(3, receiptPaymentDate);
			ps.setString(4, receiptPaymentType);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e1) {
			return null;
		}
		//mengambil id yang baru saja diinsert
		String lastInsertedIdQuery = "SELECT LAST_INSERT_ID()";
		ps = Connect.getConnection().prepareStatement(lastInsertedIdQuery);
		
		try {
			ResultSet res = Connect.getConnection().executeQuery(ps);
			res.next();
			System.out.println(res.getInt(1));
			return res.getInt(1);
		} catch (SQLException e) {
			return null;
		}
	}
	
	//UPDATE method
	public static String updateReceipt(Integer orderId, String receiptPaymentType, Double receiptPaymentAmount, Date receiptPaymentDate) {
		String preparedQuery = "UPDATE receipt SET receiptPaymentAmount = ?, receiptPaymentDate = ?, receiptPaymentType = ? WHERE orderId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(preparedQuery);
		
		try {
			ps.setDouble(1, receiptPaymentAmount);
			ps.setDate(2, receiptPaymentDate);
			ps.setString(3, receiptPaymentType);
			ps.setInt(4, orderId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e1) {
			return "Query failed";
		}
		return null;		
	}
	
	//DELETE method
	public static String deleteReceipt(Integer orderId) {
		String preparedQuery = "DELETE FROM receipt WHERE orderId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(preparedQuery);
		
		try {
			ps.setInt(1, orderId);
			Connect.getConnection().executeUpdate(ps);
		} catch (SQLException e1) {
			return "Query failed";
		}
		return null;
	}
	
	//SELECT by receipt id method
	public static Receipt getReceiptById(Integer receiptId) {
		String preparedQuery = "SELECT * FROM receipt WHERE receiptId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(preparedQuery);
		Receipt receipt = null;
		try {
			ps.setInt(1, receiptId);
			ResultSet rs = Connect.getConnection().executeQuery(ps);
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				Double receiptPaymentAmount = rs.getDouble("receiptPaymentAmount");
				Date receiptPaymentDate = rs.getDate("receiptPaymentDate");
				String receiptPaymentType = rs.getString("receiptPaymentType");
				receipt = new Receipt(receiptId, Order.getOrderByOrderId(orderId), receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
				}
		} catch (SQLException e1) {
			return null;
		}
		return receipt;
	}
	
	//SELECT all method
	public static Vector<Receipt> getAllReceipts(){
		String preparedQuery = "SELECT * FROM receipt";
		PreparedStatement ps = Connect.getConnection().prepareStatement(preparedQuery);
		Vector<Receipt> receiptList = new Vector<Receipt>();
		try {
			ResultSet rs = Connect.getConnection().executeQuery(ps);
			while(rs.next()) {
				Receipt receipt = null;
				Integer receiptId = rs.getInt("receiptId");
				Integer orderId = rs.getInt("orderId");
				Double receiptPaymentAmount = rs.getDouble("receiptPaymentAmount");
				Date receiptPaymentDate = rs.getDate("receiptPaymentDate");
				String receiptPaymentType = rs.getString("receiptPaymentType");
				receipt = new Receipt(receiptId, Order.getOrderByOrderId(orderId), receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
				receiptList.add(receipt);
				}
		} catch (SQLException e1) {
			return null;
		}
		return receiptList;
	}
}
