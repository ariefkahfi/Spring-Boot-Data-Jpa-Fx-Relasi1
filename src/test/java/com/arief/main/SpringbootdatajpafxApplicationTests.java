package com.arief.main;

import com.arief.entity.Sertifikat;
import com.arief.services.repositories.SertifikatRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdatajpafxApplicationTests {

	@Autowired
	private SertifikatRepo sRepo;


	@Test
	public void contextLoads() {
	}



}
