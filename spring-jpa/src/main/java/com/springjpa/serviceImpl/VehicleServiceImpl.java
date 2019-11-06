package com.springjpa.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dao.VehicleDao;
import com.springjpa.dto.VehicleDecUpdate;
import com.springjpa.functionalInterface.PercentageCalculation;
import com.springjpa.model.Vehicle;
import com.springjpa.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	private PercentageCalculation percentage;
	
	private static Workbook wb;
	private static Sheet sh;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static Row row;
	private static Cell cell;

	@Override
	public Vehicle addVechile(Vehicle vehicle) {
		Optional<Vehicle> vehicles = vehicleDao.findByVehicleName(vehicle.getVehicleName());
		if(!vehicles.isPresent()) {
		return vehicleDao.save(vehicle);
		}
		return new Vehicle();
	}

	@Override
	public String deleteVehicle(String vehicleName) {
		if (vehicleDao.findByVehicleName(vehicleName) != null) {
			vehicleDao.deleteVechile(vehicleName);
			return "Deleted";

		}
		return "Vehicle is not in database";

	}

	@Override
	public String updateVehicleDesc(VehicleDecUpdate vehicleDescUpdate) {
		// TODO Auto-generated method stub
		if (vehicleDao.findByVehicleName(vehicleDescUpdate.getVehicleName()) != null) {
			int i = vehicleDao.updateVehicleDesc(vehicleDescUpdate.getVehicleDesc(),
					vehicleDescUpdate.getVehicleName());
			if (i != 0) {
				return "Vehicle Desc is updated";
			}
			return "Internal issue.";
		}
		return "Vehicle is not repsent.";
	}

	@Override
	public List<Vehicle> allVehicle() {
		// TODO Auto-generated method stub
		return vehicleDao.allVehicle();
	}

	public void test() {
		float percent = percentage.percent(100, 20);
		System.out.println(percent);

	}

	@Override
	public List<Vehicle> allVehicleWithGivenYear(String year) {
		// TODO Auto-generated method stub
		List<Vehicle> allVehicles = vehicleDao.allVehicle();
		List<Vehicle> vehicleWithManufacturedYear = allVehicles.stream().filter((x) -> x.getVechileManufactureYear().equals(year)).collect(Collectors.toList());
		return vehicleWithManufacturedYear;
	}

	@Override
	public String deleteVehicleManufactureYear(String vechileManufactureYear) {
		// TODO Auto-generated method stub
		vehicleDao.deleteVehiclesOfGivenDate(vechileManufactureYear);
		return "Vehicle made in year "+vechileManufactureYear+" is now removed from database";
	}
	
	/*
	 * private String addingHeaderToExcel(Object obj) { Vehicle v = (Vehicle) obj; }
	 */

	@Override
	public String exportVehicleDataToExcel() throws Exception{
		// TODO Auto-generated method stub
		fis = new FileInputStream("C:\\Users\\P1327635\\git\\vehicleData.xlsx");
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("Sheet1");
		row = sh.createRow(0);
		
		cell = row.createCell(0);
		cell.setCellValue("vehicleId");
		
		cell = row.createCell(1);
		cell.setCellValue("vehicleName");
		
		cell = row.createCell(2);
		cell.setCellValue("vehicleType");
		
		cell = row.createCell(3);
		cell.setCellValue("vehicleDesc");
		
		cell = row.createCell(4);
		cell.setCellValue("vechileManufactureYear");
		
		List<Vehicle> allVehicles = vehicleDao.allVehicle();
		int size = allVehicles.size();
		
		for(int i = 0; i<size; i++) {
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
		
		
		fos = new FileOutputStream("C:\\Users\\P1327635\\git\\vehicleData.xlsx");
		wb.write(fos);
		fos.flush();
		
		return "Exportings";
	}

}
