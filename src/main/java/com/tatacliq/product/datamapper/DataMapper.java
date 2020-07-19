package com.tatacliq.product.datamapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

@Component
public class DataMapper {
		public String mapData(String input, List spec) throws Exception, RuntimeException {

		String transformedString = "";
		try {

			Chainr chainr = Chainr.fromSpec(spec);

			Object inputJSON = JsonUtils.jsonToObject(input);

			Object transformedOutput = chainr.transform(inputJSON);

			transformedString = JsonUtils.toJsonString(transformedOutput);
		} catch (Exception re) {
			throw new Exception("Error when transforming the JSON - " + input);
		}
		return transformedString;

	}

}
