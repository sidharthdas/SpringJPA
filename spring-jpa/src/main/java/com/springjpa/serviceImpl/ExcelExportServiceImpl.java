package com.springjpa.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.UserRepository;
import com.springjpa.dao.VehicleDao;
import com.springjpa.model.UserDetail;
import com.springjpa.model.Vehicle;
import com.springjpa.service.ExcelExportService;

@Service
public class ExcelExportServiceImpl implements ExcelExportService{
	
	private static final String RESPONSE_TEXT = "Exported Sucessfully";
	private static final String VEHICLE_VEHICLE_ID = "vehicle_vehicleId";
	private static final String USER_SALARY = "userSalary";
	private static final String USER_NAME = "userName";
	private static final String USER_ADD = "userAdd";
	private static final String USER_ID = "id";
	private static final String VECHILE_MANUFACTURE_YEAR = "vechileManufactureYear";
	private static final String VEHICLE_DESC = "vehicleDesc";
	private static final String VEHICLE_TYPE = "vehicleType";
	private static final String VEHICLE_NAME = "vehicleName";
	private static final String VEHICLE_ID = "vehicleId";
	private static final String USERDETAIL_EXCEL_DATA_PATH_ON_MAC = "/Users/sidharthdas/git/SpringJPA/UserDetailExcel.xlsx";
	private static final String VEHICLE_EXCEL_DATA_PATH_ON_MAC = "/Users/sidharthdas/git/SpringJPA/VehicleExcel.xlsx";
	private static final String USERDETAIL_EXCEL_DATA_PATH_ON_WIN = "C:\\Users\\P1327635\\git\\UserDetailData.xlsx";
	private static final String VEHICLE_EXCEL_DATA_PATH_ON_WIN = "C:\\Users\\P1327635\\git\\vehicleData.xlsx";

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private UserRepository userRepository;
	
	private static Workbook wb;
	private static Sheet sh;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static Row row;
	private static Cell cell;

	@Override
	public String vehicleDataExport() throws Exception{
		// TODO Auto-generated method stub
		fis = new FileInputStream(VEHICLE_EXCEL_DATA_PATH_ON_MAC); //for mac
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
		row = sh.createRow(0);
		
		cell = row.createCell(0);
		cell.setCellValue(VEHICLE_ID);
		
		cell = row.createCell(1);
		cell.setCellValue(VEHICLE_NAME);
		
		cell = row.createCell(2);
		cell.setCellValue(VEHICLE_TYPE);
		
		cell = row.createCell(3);
		cell.setCellValue(VEHICLE_DESC);
		
		cell = row.createCell(4);
		cell.setCellValue(VECHILE_MANUFACTURE_YEAR);
		
		List<Vehicle> allVehicles = vehicleDao.allVehicle();
		
		int size = allVehicles.size();
		for(int i = 0; i < size; i++) {
			Vehicle v = allVehicles.get(i);
			row = sh.createRow(i+1);
			
			cell = row.createCell(0);
			cell.setCellValue(v.getVehicleId());
			
			cell = row.createCell(1);
			cell.setCellValue(v.getVehicleName());
			
			cell = row.createCell(2);
			cell.setCellValue(v.getVehicleType());
			
			cell = row.createCell(3);
			cell.setCellValue(v.getVehicleDesc());
			
			cell = row.createCell(4);
			cell.setCellValue(v.getVechileManufactureYear());
			
		}
		
		fos = new FileOutputStream(VEHICLE_EXCEL_DATA_PATH_ON_MAC);
		wb.write(fos);
		fos.flush();
		return RESPONSE_TEXT;
	}

	@Override
	public String userDetailDataExport() throws Exception{
		// TODO Auto-generated method stub
		fis = new FileInputStream(USERDETAIL_EXCEL_DATA_PATH_ON_MAC); //for mac
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
		row = sh.createRow(0);
		
		cell = row.createCell(0);
		cell.setCellValue(USER_ID);
		
		cell = row.createCell(1);
		cell.setCellValue(USER_ADD);
		
		cell = row.createCell(2);
		cell.setCellValue(USER_NAME);
		
		cell = row.createCell(3);
		cell.setCellValue(USER_SALARY);
		
		cell = row.createCell(4);
		cell.setCellValue(VEHICLE_VEHICLE_ID);
		
		List<UserDetail> allUsers = userRepository.allUsers();
		
		int size = allUsers.size();
		System.out.println(size);
		for(int i = 0; i< size; i++) {
			UserDetail ud = allUsers.get(i);
			
			row = sh.createRow(i+1);
			
			cell = row.createCell(0);
			cell.setCellValue(ud.getId());
			
			cell = row.createCell(1);
			cell.setCellValue(ud.getUserAdd());
			
			cell = row.createCell(2);
			cell.setCellValue(ud.getUserName());
			
			cell = row.createCell(3);
			cell.setCellValue(ud.getUserSalary());
			
			/*
			 * cell = row.createCell(4); cell.setCellValue(ud.getVehicle().getVehicleId());
			 */
			
		}
		
		fos = new FileOutputStream(USERDETAIL_EXCEL_DATA_PATH_ON_MAC);
		wb.write(fos);
		fos.flush();
		return RESPONSE_TEXT;
	}

}
