package com.test.sayHelloWorld.main;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;

import javax.sql.DataSource;

import com.fss.dictionary.Dictionary;
import com.fss.sql.Database;
import com.fss.thread.ProcessorListener;
import com.fss.thread.ThreadManager;
import com.fss.thread.ThreadProcessor;
import com.fss.util.FileUtil;
import com.fss.util.LogOutputStream;
import com.fss.util.StringUtil;
import com.test.sayHelloWorld.threads.ThreadManagerEx;

import oracle.jdbc.pool.OracleDataSource;

/**
 * @author hazyflame
 *
 */
public class HFApplicationMain implements ProcessorListener {
	public static ThreadManagerEx threadManagerEx = null;
	public static final String CONFIG_FILE_NAME = "conf/ServerConfig.txt";
	public static final String VALIDATE_FILE_NAME = "conf/validate.properties";

	public static String USER_NAME = "";
	public static String PASSWORD = "";
	public static DataSource datasource = null;

	private static Dictionary mdc = null;
	private static Dictionary mdValidate = null;
	

	public Connection getConnection() throws Exception {
		return datasource.getConnection();
	}

	public void onCreate(ThreadProcessor threadProcessor) throws Exception {
		
	}

	public void onOpen(ThreadProcessor threadProcessor) throws Exception {
		threadProcessor.mcnMain = getConnection();
	}

	public String getParameter(String strKey) {
		return StringUtil.nvl(mdc.getString(strKey), "");
	}

	public static Dictionary getDic() {
		return mdc;
	}

	public static Dictionary getValidateDic() {
		return mdValidate;
	}

	public DataSource createDataSource(Dictionary dic) throws Exception {
		OracleDataSource oradatasource = new OracleDataSource();

		oradatasource.setURL(dic.getString("Url"));
		oradatasource.setUser(dic.getString("UserName"));
		oradatasource.setPassword(dic.getString("Password"));

		Connection conn = oradatasource.getConnection();

		if (conn.isClosed()) {
			throw new Exception("Creating datasource failed");
		}
		Database.closeObject(conn);
		return oradatasource;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			HFApplicationMain lsn = new HFApplicationMain();
			String strWorkingDir = System.getProperty("user.dir");

			if (strWorkingDir == null || strWorkingDir.equals("")) {
				strWorkingDir = System.getProperty("user.dir");
			}
			if (!strWorkingDir.endsWith("/") || !strWorkingDir.endsWith("\\")) {
				strWorkingDir += "/";
			}

			mdc = new Dictionary(CONFIG_FILE_NAME);
			mdValidate = new Dictionary(VALIDATE_FILE_NAME);

			String strLogFile = mdc.getString("LogFile");
			String strOutputFile = mdc.getString("OutputFile");
			String strLogDir = strWorkingDir + mdc.getString("LogDir");

			if (!strLogDir.endsWith("/") || !strLogDir.endsWith("\\")) {
				strLogDir += "/";
			}
			File f = new File(strLogDir + strLogFile);
			FileUtil.forceFolderExist(f.getParent());

			lsn.USER_NAME = mdc.getString("ServiceUserName");
			lsn.PASSWORD = mdc.getString("ServicePassword");

			// ket noi DB oracle
			datasource = lsn.createDataSource(mdc);
			PrintStream ps = new PrintStream(new LogOutputStream(strLogDir + strLogFile));
			PrintStream psOutput = new PrintStream(new LogOutputStream(strLogDir + strOutputFile));

			System.setOut(psOutput);
			System.setErr(ps);

			// Start manager
			ThreadManagerEx cs = new ThreadManagerEx(Integer.parseInt(mdc.getString("PortID")), lsn);

			cs.setLoadingMethod(ThreadManager.LOAD_FROM_FILE_AND_DB);
			cs.start();

			threadManagerEx = cs;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
