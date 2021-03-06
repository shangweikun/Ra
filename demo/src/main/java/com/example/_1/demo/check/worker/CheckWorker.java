package com.example._1.demo.check.worker;

import com.example._1.demo.chooser.AbstractChooser;
import com.example._1.demo.check.AbstractChecker;
import com.example._1.demo.collect.Collector;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@SuppressWarnings("rawtypes")
public class CheckWorker<R> extends AbstractChooser<CheckWorker> {

	protected AbstractChecker<R> checker;

	protected Collector<?, R> collector;

	public CheckWorker(AbstractChecker<R> checker,
	                   Collector<?, R> collector) {
		this.checker = checker;
		this.collector = collector;
	}
}


