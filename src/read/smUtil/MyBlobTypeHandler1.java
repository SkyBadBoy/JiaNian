package read.smUtil;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import wtb.smUtil.ErrorUtil;

import com.mysql.jdbc.Blob;

public class MyBlobTypeHandler1 extends BaseTypeHandler<String> {
    //###指定字符集
    private static final String DEFAULT_CHARSET = "utf-8";

    @Override
    public String getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Blob blob = (Blob) rs.getBlob(columnName);
        byte[] returnValue = null;
        if (null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
           //把byte转化成string
            return new String(returnValue, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
        	ErrorUtil.HandleError(null, "wtb.smUtil.MyBlobTypeHandler.getNullableResult", e);
            throw new RuntimeException("Blob Encoding Error!");
        }
    }

	@Override
	public String getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		 Blob blob = (Blob) arg0.getBlob(arg1);
	        byte[] returnValue = null;
	        if (null != blob) {
	            returnValue = blob.getBytes(1, (int) blob.length());
	        }
	        try {
	            return new String(returnValue, DEFAULT_CHARSET);
	        } catch (UnsupportedEncodingException e) {
	        	ErrorUtil.HandleError(null, "wtb.smUtil.MyBlobTypeHandler.getNullableResult", e);
	            throw new RuntimeException("Blob Encoding Error!");
	        }
	}

	@Override
	public void setNonNullParameter(java.sql.PreparedStatement arg0, int arg1, String arg2, JdbcType arg3) throws SQLException {
		ByteArrayInputStream bis;
        try {
            //###把String转化成byte流
            bis = new ByteArrayInputStream(arg2.getBytes(DEFAULT_CHARSET));
        } catch (UnsupportedEncodingException e) {
        	ErrorUtil.HandleError(null, "wtb.smUtil.MyBlobTypeHandler.setNonNullParameter", e);
            throw new RuntimeException("Blob Encoding Error!");
        }   
        arg0.setBinaryStream(arg1, bis, arg2.length());
		
	}

	@Override
	public String getNullableResult(java.sql.CallableStatement arg0, int arg1) throws SQLException {
		return null;
	}
}