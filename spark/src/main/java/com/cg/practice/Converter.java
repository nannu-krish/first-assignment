package com.cg.practice;

import spark.ResponseTransformer;

import com.google.gson.Gson;

public class Converter  implements ResponseTransformer{
	


	@Override
public String render(Object data) throws Exception {
	return new Gson().toJson(data);
}
}
