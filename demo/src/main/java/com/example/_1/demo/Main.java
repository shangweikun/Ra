package com.example._1.demo;

import cn.hutool.core.util.ObjectUtil;
import com.example._1.demo.check.worker.CheckWorker;
import com.example._1.demo.check.worker.FinalCheckWorker;
import com.example._1.demo.check.AbstractChecker;
import com.example._1.demo.chooser.AbstractChooser;
import com.example._1.demo.check.impl.ClassNameChecker;
import com.example._1.demo.check.impl.PersonIdChecker;
import com.example._1.demo.collect.Collector;
import com.example._1.demo.collect.impl.PersonCollector;
import com.example._1.demo.dao.PersonDAO;
import com.example._1.demo.entity.Person;

public class Main {

	private PersonDAO dao;

	public CheckWorker<Person> personAdjudicator;

	public void init() {
		CheckWorker<Person> idAdjudicator = new CheckWorker<>(
				new PersonIdChecker(),
				new PersonCollector(dao)
		);
		CheckWorker<Person> endPoint = new FinalCheckWorker<>(true);
		CheckWorker<Person> classNameAdjudicator = new CheckWorker<>(
				new ClassNameChecker(),
				null
		);
		idAdjudicator.setIsTrueChoose(endPoint);
		idAdjudicator.setIsFalseChoose(classNameAdjudicator);
		classNameAdjudicator.setIsTrueChoose(endPoint);
		this.personAdjudicator = idAdjudicator;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	public static boolean checkMainProcess(CheckWorker adjudicator,
	                                       Object input) {

		Object param = input;
		while (ObjectUtil.isNotEmpty(adjudicator)) {

			if (adjudicator instanceof FinalCheckWorker) {
				return ((FinalCheckWorker<?>) adjudicator).result();
			}

			AbstractChecker checker = adjudicator.getChecker();
			Collector collector = adjudicator.getCollector();
			adjudicator = ((AbstractChooser<CheckWorker>) adjudicator)
					.choose(
							checker.check(
									ObjectUtil.isNotEmpty(collector) ?
											param = collector.collectInfo(param) :
											param)
					);
		}

		return false;
	}

	public static void main(String[] args) {
		Main app = new Main();
		app.init();
		String input = "PT050120";
		CheckWorker<Person> adjudicator = app.personAdjudicator;
		System.out.println(checkMainProcess(adjudicator, input));
	}
}
