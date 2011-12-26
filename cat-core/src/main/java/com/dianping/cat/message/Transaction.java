package com.dianping.cat.message;

/**
 * <p>
 * <code>Transaction</code> is any interesting unit of work that takes time to
 * complete and may fail.
 * </p>
 * 
 * <p>
 * Basically, all data access across the boundary needs to be logged as a
 * <code>Transaction</code> since it may fail and time consuming. For example,
 * URL request, disk IO, JDBC query, search query, HTTP request, 3rd party API
 * call etc.
 * </p>
 * 
 * <p>
 * Sometime if A needs call B which is owned by another team, although A and B
 * are deployed together without any physical boundary. To make the ownership
 * clear, there could be some <code>Transaction</code> logged when A calls B.
 * </p>
 * 
 * <p>
 * Most of <code>Transaction</code> should be logged in the infrastructure level
 * or framework level, which is transparent to the application.
 * </p>
 * 
 * <p>
 * All CAT message will be constructed as a message tree and send to back-end
 * for further analysis, and for monitoring. Only <code>Transaction</code> can
 * be a tree node, all other message will be the tree leaf.　The transaction
 * without other messages nested is an atomic transaction.
 * </p>
 * 
 * @author Frankie Wu
 */
public interface Transaction extends Message {
	public long getDuration();
}
