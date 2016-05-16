package pers.fanxin.carmanagment.module.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import pers.fanxin.carmanagement.module.dao.ApplicationDAO;
import pers.fanxin.carmanagement.module.dao.ApproveDAO;
import pers.fanxin.carmanagement.module.dao.CarDAO;
import pers.fanxin.carmanagement.module.dao.DriverDAO;
import pers.fanxin.carmanagement.module.dao.RouteLogDAO;
import pers.fanxin.carmanagement.module.entity.Application;
import pers.fanxin.carmanagement.module.entity.Approve;
import pers.fanxin.carmanagement.module.entity.Car;
import pers.fanxin.carmanagement.module.entity.Driver;
import pers.fanxin.carmanagement.module.entity.RouteLog;

public class ApplicationDAOTest {
	ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resource/test-*.xml");
	ApplicationDAO applicationDAO = (ApplicationDAO)context.getBean("applicationDAOImpl");
	CarDAO carDAO = (CarDAO)context.getBean("carDAOImpl");
	ApproveDAO approveDAO = (ApproveDAO)context.getBean("approveDAOImpl");
	RouteLogDAO routeLogDAO = (RouteLogDAO)context.getBean("routeLogDAOImpl");
	DriverDAO driverDAO = (DriverDAO)context.getBean("driverDAO");
	@Before
	public void setUp() throws Exception {
	}

//	@Test
	public void carDAOTest() {
		Car car = new Car();
		car.setCarName("大众");
		car.setDescription("大众");
		car.setState("available");
		carDAO.save(car);
	}
	
//	@Test
	public void applicationDAOTest(){
		Application application = new Application();
		application.setApplicantName("admin");
		application.setApplyDate(new Date());
		application.setDestination("成都");
		application.setApplicantId(new Long(1));
		application.setRemark("无");
		application.setRoundtrip(true);
		application.setStartpoint("北京");
		application.setState("unproved");
		applicationDAO.createApplication(application);
	}
//	@Test
	public void approveDAOTest(){
		Approve approve = new Approve();
		approve.setApproveDate(new Date());
		approve.setApproverId(new Long(1));
		approve.setApproverName("admin");
//		approve.setDriverName("admin");
//		approve.setDriverId(new Long(1));
		Application application= applicationDAO.getApplicationById(1);
		application.setState("approved");
		application.setApprove(approve);
		approve.setApplication(application);
		approveDAO.save(approve);
	}
	
	@Test
	public void routeLogDAOTest(){
		RouteLog routeLog= new RouteLog();
		routeLog.setApplication(applicationDAO.getApplicationById(1));
		routeLog.setCar(carDAO.getCarById(1));
		routeLog.setCost(100.00);
		routeLog.setDriverId(new Long(1));
		routeLog.setDriverName("admin");
		routeLog.setEndDate(new Date());
		routeLog.setStartDate(new Date());
		Application application= applicationDAO.getApplicationById(1);
		application.setRouteLog(routeLog);
		application.setState("complete");
		routeLog.setApplication(application);
		routeLogDAO.createRouteLog(routeLog);
	}
	
	public void driverDAOTest(){
		Driver driver = new Driver();
//		driver.setCurrentRouteLog(currentRouteLog);
	}

}
