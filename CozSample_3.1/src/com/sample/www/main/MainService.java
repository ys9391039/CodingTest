package com.sample.www.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mainService")
public class MainService {
	//protected static Log logger = LogFactory.getLog(AdminServiceImpl.class);
	
	@Autowired
	private MainMapper mainMapper;
	
	//@Transactional (value="transactionManager")
	public String getMainInfo() {
		String mainInfo = mainMapper.getMainInfo();
		//String mainInfo = "12341234";

		return mainInfo;
	}

	public String updateMainInfo() throws Exception{
			mainMapper.txTest1();
			mainMapper.txTest2();
		
		return "12341234";
	}
}