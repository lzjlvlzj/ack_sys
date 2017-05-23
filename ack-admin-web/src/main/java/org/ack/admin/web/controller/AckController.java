package org.ack.admin.web.controller;

import java.io.Serializable;


import org.ack.base.web.BaseController;

/**
 * 
 * 
 * @author ack
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AckController<T extends Object, PK extends Serializable>
		extends BaseController<T, PK> {
	

}
