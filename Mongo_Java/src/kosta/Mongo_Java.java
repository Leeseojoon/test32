package kosta;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class Mongo_Java {

	public static void main(String[] args) {
		Mongo conn = null;
		try {
			conn = new MongoClient("localhost", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		WriteConcern w = new WriteConcern(0, 2000);
		conn.setWriteConcern(w);
		
		DB db = conn.getDB("kosta");
		DBCollection collection = db.getCollection("users");				
		
		//insertUser(db);//������ ����
		
		findUser(collection, "��浿");

	}
	
	public static void insertUser(DB db){
		DBCollection col = db.getCollection("users");
		DBObject doc = new BasicDBObject();
		doc.put("username", "��浿");
		doc.put("country", "�̱�");
		
		DBObject favorities = new BasicDBObject();
		favorities.put("movies", new String[]{"��������", "���� ���� �Ҹ��ϳ�"});
		favorities.put("sports", new String[]{"��", "����"});
		doc.put("favorities", favorities);
		
		col.insert(doc);
		
		
	}
	
	
	public static void findUser(DBCollection collection, String name){
		DBObject doc = new BasicDBObject();
		doc.put("username", name);
		DBCursor cursor = collection.find(doc);
		DBObject user = null;
		
		while(cursor.hasNext()){
			user = cursor.next();
			System.out.println((ObjectId)user.get("_id"));
			System.out.println(user.get("username"));
			
			DBObject favorities = (DBObject)user.get("favorities");
			if(favorities.containsField("movies")){
				DBObject movies = (DBObject)favorities.get("movies");
				System.out.println(movies);
			}
			
			if(favorities.containsField("sports")){
				DBObject sports = (DBObject)favorities.get("sports");
				System.out.println(sports);
			}
			
			
		}
		
	}

}











