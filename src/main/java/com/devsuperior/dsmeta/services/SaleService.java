package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> report(String minDate, String maxDate, String name, Pageable pageable){
		LocalDate minLocalDate;
		LocalDate maxLocalDate;

		if(maxDate.isEmpty()){
			maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			maxLocalDate = LocalDate.parse(maxDate);
		}
		if(minDate.isEmpty()){
			minLocalDate = maxLocalDate.minusYears(1L);
		}else{
			minLocalDate = LocalDate.parse(minDate);
		}
		return repository.searchReport(name, minLocalDate, maxLocalDate, pageable);
	}

	public Page<SaleSummaryDTO> summary(String minDate, String maxDate, Pageable pageable){
		LocalDate minLocalDate;
		LocalDate maxLocalDate;

		if(maxDate.isEmpty()){
			maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			maxLocalDate = LocalDate.parse(maxDate);
		}
		if(minDate.isEmpty()){
			minLocalDate = maxLocalDate.minusYears(1L);
		}else{
			minLocalDate = LocalDate.parse(minDate);
		}
		return repository.searchSummary(minLocalDate, maxLocalDate, pageable);
	}
}
