package com.htap.fw_core;

/**
 * Generic base class that allows users to declare variables with data type of their choice.
 * <p>
 * It allows users to set and get the values to and from these user defined variables.
 * <p>
 * This generic class is defined specifically to be used by verification functions where
 * the data being verified might vary.<br>
 * It could be a String, Integer or Boolean.
 * <p>
 * <b>Usage:</b>
 * <pre>
 * {@code
 * FW_GenericDataType<String> expected = new FW_GenericDataType<String>();
 * FW_GenericDataType<Integer> actual = new FW_GenericDataType<Integer>();
 * FW_GenericDataType<Boolean> flag = new FW_GenericDataType<Boolean>();
 * }
 * </pre>
 * @param T The type of the variable being defined - String, Integer, Boolean etc.
 * <p>
 * @author Rohit Kothari
 */

//We use < > to specify Parameter type
public class FW_GenericDataType<T> {
	// An object of type T is declared
	private T t;

	/**
	 * Returns the value for variable of type T.
	 * <p>
	 * <b>Usage:</b>
	 * <pre>
	 * {@code
	 * FW_GenericDataType<String> expectedLabel = new FW_GenericDataType<String>();
	 * expectedLabel.setT(HomePage.getPageTitle());
	 * expectedLabel.getT();
	 * }
	 * </pre>
	 * @return <code>String</code> if <code>T</code> is String<br>
	 * <code>Integer</code> if <code>T</code> is Integer<br>
	 * <code>Boolean</code> if <code>T</code> is Boolean
	 */

	public T getT(){
		return t;
	}

	/**
	 * Sets the value for the variable of type T.
	 * <p>
	 * @param t Value for the variable of type T.
	 * <p>
	 * <b>Usage:</b>
	 * <pre>
	 * {@code
	 * FW_AnyType<String> expectedLabel = new FW_AnyType<String>();
	 *	expectedLabel.setT(HomePage.getPageTitle());
	 * expectedLabel.getT();
	 * }
	 * </pre>
	 */
	public void setT(T t){
		this.t=t;
	}

}