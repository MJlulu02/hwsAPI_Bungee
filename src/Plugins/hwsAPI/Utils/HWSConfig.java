package Plugins.hwsAPI.Utils;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import redis.clients.jedis.Jedis;

public class HWSConfig {

	private String mongo_ip = null, mongo_usr = null, mongo_pass = null;
	private int mongo_port = 27018;
	private String redis_ip = null, redis_pass = null;
	private int redis_port = 6379;

	private Jedis jedisclient = null;
	private MongoClient mongoclient;
	private DB mcserverdb;
	private DBCollection mdb_players = null;

	public Jedis GetJedis() {
		if (jedisclient != null && this.redis_pass != null && this.jedisclient.isConnected()) {
			jedisclient.auth(this.redis_pass);
		}
		return jedisclient;
	}
	
	public DBCollection getMongoDB() {
		return this.mdb_players;
	}

	public int getMongo_port() {
		return mongo_port;
	}

	public void setMongo_port(int mongo_port) {
		this.mongo_port = mongo_port;
	}

	public int getRedis_port() {
		return redis_port;
	}

	public void setRedis_port(int redis_port) {
		this.redis_port = redis_port;
	}

	public String getMongo_ip() {
		return mongo_ip;
	}

	public void setMongo_ip(String mongo_ip) {
		this.mongo_ip = mongo_ip;
	}

	public String getMongo_usr() {
		return mongo_usr;
	}

	public void setMongo_usr(String mongo_usr) {
		this.mongo_usr = mongo_usr;
	}

	public String getMongo_pass() {
		return mongo_pass;
	}

	public void setMongo_pass(String mongo_pass) {
		this.mongo_pass = mongo_pass;
	}

	public String getRedis_ip() {
		return redis_ip;
	}

	public void setRedis_ip(String redis_ip) {
		this.redis_ip = redis_ip;
	}

	public String getRedis_pass() {
		return redis_pass;
	}

	public void setRedis_pass(String redis_pass) {
		this.redis_pass = redis_pass;
	}

	@SuppressWarnings("deprecation")
	public boolean connect_mongo() {
		if(this.mongoclient != null) {
			return true;
		}else if (this.getMongo_ip() == null || this.getMongo_usr() == null || this.getMongo_pass() == null) {
			return false;
		}
		// Init Connection
		this.mongoclient = new MongoClient(this.getMongo_ip(), this.getMongo_port());

		try {
			this.mcserverdb = this.mongoclient.getDB("Hws_DataBase");
			this.mdb_players = this.mcserverdb.getCollection("Hws_PlayerData");
			this.mdb_players.count();
		} catch (Exception e) {
			  this.mongoclient = null;
			  return false;
		}

		return true;
	}

	public boolean connect_redis() {
		if(this.jedisclient != null) {
			return true;
		}else if (this.getRedis_ip() == null) {
			return false;
		}
		// Init Connection
		this.jedisclient = new Jedis(this.getRedis_ip(), this.getRedis_port());
		if (!this.jedisclient.isConnected()) {
			this.jedisclient = null;
			return false;
		}
			

		return true;
	}

}
