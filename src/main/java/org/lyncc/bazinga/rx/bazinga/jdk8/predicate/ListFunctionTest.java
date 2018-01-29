package org.lyncc.bazinga.rx.bazinga.jdk8.predicate;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ListFunctionTest {
	
	private static List<Values> params = new ArrayList<>();
	
	static {
		params.add(new Values(1, 2));
		params.add(new Values(31, 4));
		params.add(new Values(5, 6));
		params.add(new Values(7, 8));
		params.add(new Values(91, 10));
		params.add(new Values(11, 12));
	}
	
	
	public static void main(String[] args) {
		ListFunction.filter(params, (Values v) -> v.getValue1() > v.getValue2())
		.forEach((Values eachValue) -> System.out.println(eachValue));
		
		ListFunction.consumer(params, (Values values) -> System.out.println(values));
		
		ListFunction.mapping(params, (Values eachValues) -> new Result(eachValues.getValue1() + eachValues.getValue2())).forEach((Result e) -> System.out.println(e));
		
	}
	
	
	
	public static class Values {
		
		private Integer value1;
		
		private Integer value2;
		
		public Values(Integer value1, Integer value2) {
			this.value1 = value1;
			this.value2 = value2;
		}

		public Integer getValue1() {
			return value1;
		}

		public void setValue1(Integer value1) {
			this.value1 = value1;
		}

		public Integer getValue2() {
			return value2;
		}

		public void setValue2(Integer value2) {
			this.value2 = value2;
		}
		
		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}
		
	}
	
	public static class Result {
		
		private Integer sum;
		
		public Result(Integer sum) {
			this.sum = sum;
		}

		public Integer getSum() {
			return sum;
		}

		public void setSum(Integer sum) {
			this.sum = sum;
		}
		
		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}
	}

}
