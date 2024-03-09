package com.ucl.infra.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

	@Autowired
	IndexDao dao;
}
