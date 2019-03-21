package com.lalsberg.ops;

import java.util.List;

public class Controller {

	private ResolutionResolver resolutionResolver;

	public List<Resolution> findResolution(Ops ops) {
		return resolutionResolver.findPossibleResolutions(ops);
	}

}
