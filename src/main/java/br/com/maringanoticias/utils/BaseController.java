package br.com.maringanoticias.utils;

public abstract class BaseController<TService extends BaseService<?, ?>> {

	protected TService service;

	public BaseController(TService service) {
		this.service = service;
	}

}
