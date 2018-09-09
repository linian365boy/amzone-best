package com.szyoy.amazon.utils;

import com.szyoy.amazon.model.Product;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niange
 * @ClassName: Tools
 * @desp:
 * @date: 2018/8/21 下午10:12
 * @since JDK 1.7
 */
public class Tools {

    private final static int BATCH_SIZE = 100;
    static Connection con = MySQLConnections.getConnection();
    static PreparedStatement stmt = null;

    public static void readCsv(String filePath){
        File csv = new File(filePath);
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(csv));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        String line;
        try{
            Product product;
            List<Product> products = new ArrayList<>(BATCH_SIZE);
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] lineStr = strToArray(line);
                if(StringUtils.isBlank(lineStr[2]) && StringUtils.isBlank(lineStr[3])){
                    continue;
                }
                product = new Product();
                product.setParentCategory(lineStr[0]);
                product.setCategory(lineStr[1]);
                product.setProductName(lineStr[2]);
                product.setUrl(lineStr[3]);
                if(StringUtils.isNotBlank(lineStr[4]) && !StringUtils.equals(lineStr[4], "----")) {
                    product.setReviews(Integer.parseInt(lineStr[4].replace(",", "")));
                }
                product.setStars(lineStr[5]);
                if(StringUtils.isNotBlank(lineStr[6]) && !StringUtils.equals(lineStr[6], "----")) {
                    if(lineStr[6].indexOf(",") > 0){
                        lineStr[6] = lineStr[6].replace(",", "");
                    }
                    if(lineStr[6].indexOf("-") > 0){
                        product.setPrice(Double.parseDouble(lineStr[6].split("-")[0].replace("$", "")));
                    }else {
                        product.setPrice(Double.parseDouble(lineStr[6].replace("$", "")));
                    }
                }
                product.setImageUrl(lineStr[7]);
                products.add(product);
                if(products.size() >= BATCH_SIZE){
                    // flush to db
                    try {
                        flushToDb(products);
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    products.clear();
                }
            }
            if(products.size() > 0) {
            	// flush to db
                try {
                    flushToDb(products);
                }catch (SQLException e){
                }
                products.clear();
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    

    public static void flushToDb(List<Product> products) throws SQLException {
        stmt = con.prepareStatement("insert into tb_product(id, productName, imageUrl, category, parentCategory, " +
                "url, reviews, stars, price, ctime) values(null,?,?,?,?,?,?,?,?,?)");
        con.setAutoCommit(false);
        for(int i=0;i<products.size();i++){
            stmt.setString(1, products.get(i).getProductName());
            stmt.setString(2, products.get(i).getImageUrl());
            stmt.setString(3, products.get(i).getCategory());
            stmt.setString(4, products.get(i).getParentCategory());
            stmt.setString(5, products.get(i).getUrl());
            stmt.setInt(6, products.get(i).getReviews());
            stmt.setString(7, products.get(i).getStars());
            stmt.setDouble(8, products.get(i).getPrice());
            stmt.setLong(9, System.currentTimeMillis()/1000);
            stmt.addBatch();
        }
        try {
        	stmt.executeBatch();
        }catch(Exception e) {
        	System.err.println(e.getMessage());
        }finally {
        	stmt.close();
        	con.commit();
        }
    }

    private static String[] strToArray(String strLine) {
        String strItem = "";
        int semicolonFlg = 0;
        List<String> lstStr = new ArrayList<>();
        String strA;
        for (int i = 0; i < strLine.length(); i++) {
            strA = strLine.substring(i, i+1);
            if (strA.equals("\"")) semicolonFlg += 1;
            if (semicolonFlg == 2) semicolonFlg = 0;
            if (strA.equals(",") && semicolonFlg == 0) {
                if (strItem.startsWith("\"") && strItem.endsWith("\"")) {
                    strItem = strItem.substring(1, strItem.length() - 1);
                }
                lstStr.add(strItem);
                strItem = "";
            } else {
                strItem += strA;
            }
        }
        if (strItem.length() > 0) {
            if (strItem.startsWith("\"") && strItem.endsWith("\"")) {
                strItem = strItem.substring(1, strItem.length() - 1);
            }
            lstStr.add(strItem);
        }

        return lstStr.toArray(new String[]{});
    }


    public static void main(String[] args){
        String filePath = "/Users/linian/Documents";
        File directory = new File(filePath);
        String[] fileNames = directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.lastIndexOf(".csv") > 0){
                    return true;
                }
                return false;
            }
        });
        for(String fileName : fileNames) {
        	long start = System.currentTimeMillis();
            readCsv(filePath + File.separator + fileName);
            System.out.println("fileName=> "+ fileName +" cost time => "+(System.currentTimeMillis() - start) + "ms");
        }
    }
}
