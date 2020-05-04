package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.demo.aspect.AuthorizeHandlerAspect;


/**
 * アクセス制御を定義するアノテーション。
 * <p>コントローラの<code>RequestMapping</code>を定義しているメソッド、クラスに設定してください。
 * <p>以下のチェックを行う。
 *  <ol>
 *   <li>ユーザがログインしているかどうか。ログインしていない場合、{@link SessionTimeoutException}をスローする。
 *   <li>ログインユーザが指定した何れかの権限を持っているかどうか。権限を持っていない場合、{@link InvalidRoleException}をスローする。
 * <p>クラスとメソッドの両方にアノテーションが付加された場合、どちらからの権限を持っていれば認可される。
 * <p>判定処理は、{@link AuthorizeHandlerAspect}で行う。
 *
 */

@Target(value={ElementType.METHOD, ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
@Documented
public @interface Authorize {

   	/**
	 * 権限を定義する。
	 * <p>
	 * 書式は<code>操作コード@サービスコード</code>。
	 * <p>
	 * 操作コードは省略が可能。
	 *
	 * @return
	 */
	String[] roles() default {};

}
