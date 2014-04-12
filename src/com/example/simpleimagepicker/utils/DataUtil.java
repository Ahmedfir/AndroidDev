package com.example.simpleimagepicker.utils;

import java.util.Collection;
import java.util.List;

/**
 * Utility class provides methods for data processing.
 * 
 * @see http 
 *      ://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec
 *      .html#Cipher
 */
public final class DataUtil {

	/**
	 * Internal constructor; not to be called as this class provides static
	 * utilities only.
	 */
	private DataUtil() {
		throw new UnsupportedOperationException("No instances permitted");
	}

	/**
	 * Test if a given string is empty.
	 * 
	 * @param s
	 *            the string to test
	 * @return <code> true </code> string is empty, otherwise
	 *         <code> false </code>.
	 */
	public static boolean isEmpty(final String s) {
		return (s == null || s.length() == 0);
	}

	/**
	 * Test if a given string is not empty.
	 * 
	 * @param s
	 *            the string to test
	 * @return <code> true </code> string is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static boolean isNotEmpty(final String s) {
		return (s != null && s.length() != 0);
	}

	/**
	 * Test if a given collection is not empty.
	 * 
	 * @param <T>
	 *            the type of the collection.
	 * @param collection
	 *            to test
	 * @return <code> true </code> collection is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isNotEmpty(final Collection<T> collection) {
		return (collection != null && !collection.isEmpty());
	}

	/**
	 * Test if a given collection is empty.
	 * 
	 * @param <T>
	 *            the type of the collection.
	 * @param collection
	 *            to test
	 * @return <code> true </code> collection is empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isEmpty(final Collection<T> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * Test if a given arguments is not empty.
	 * 
	 * @param <T>
	 *            the type of the arguments.
	 * @param args
	 *            to test
	 * @return <code> true </code> arguments is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isNotEmpty(final T... args) {
		return (args != null && args.length != 0);
	}

	/**
	 * Test if a given arguments is empty.
	 * 
	 * @param <T>
	 *            the type of the arguments.
	 * @param args
	 *            to test
	 * @return <code> true </code> arguments is empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isEmpty(final T... args) {
		return (args == null || args.length == 0);
	}

	/**
	 * Test if a given argument is not empty.
	 * 
	 * @param <T>
	 *            the type of the argument.
	 * @param t
	 *            to test
	 * @return <code> true </code> argument is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isNotEmpty(final T t) {
		return (t != null);
	}

	/**
	 * Test if a given argument is not empty.
	 * 
	 * @param <T>
	 *            the type of the argument.
	 * @param t
	 *            to test
	 * @return <code> true </code> argument is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isEmpty(final T t) {
		return (t == null);
	}

	/**
	 * Test if a given list is not empty.
	 * 
	 * @param <T>
	 *            the type of the list.
	 * @param list
	 *            to test
	 * @return <code> true </code> list is not empty, otherwise
	 *         <code> false </code>.
	 */
	public static <T> boolean isNotEmpty(final List<T> list) {
		return (list != null && !list.isEmpty());
	}

	/**
	 * Test if a given list is empty.
	 * 
	 * @param <T>
	 *            the type of the list.
	 * @param list
	 *            to test
	 * @return <code> true </code> list is empty, otherwise <code> false </code>
	 */
	public static <T> boolean isEmpty(final List<T> list) {
		return (list == null || list.isEmpty());
	}

	/**
	 * Get the number of elements.
	 * 
	 * @param args
	 * @return the number of elements.
	 */
	public static <T> int sizeOf(T... args) {
		return args != null ? args.length : 0;
	}

	/**
	 * Get the number of characters.
	 * 
	 * @param s
	 * @return
	 */
	public static <T> int sizeOf(String s) {
		return s != null ? s.length() : 0;
	}

	/**
	 * Get the number of elements.
	 * 
	 * @param list
	 * @return the number of elements.
	 */
	public static <T> int sizeOf(List<T> list) {
		return list != null ? list.size() : 0;
	}

	/**
	 * Get the number of elements.
	 * 
	 * @param collection
	 * @return the number of elements.
	 */
	public static <T> int sizeOf(Collection<T> collection) {
		return collection != null ? collection.size() : 0;
	}

}
