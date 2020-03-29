package Plugins.hwsAPI.DataManageur;

import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import Plugins.hwsAPI.PluginsManageur.HWSAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import redis.clients.jedis.Jedis;

public class DataManageur {

	private ProxiedPlayer p;
	private HashMap<String, String> HashData = new HashMap<String, String>();

	private DBCollection MongoDB_players = new HWSAPI().hwsConfig.getMongoDB();
	private Jedis jedis = new HWSAPI().hwsConfig.GetJedis();

	public DataManageur(ProxiedPlayer p) {
		this.p = p;
	}

	@SuppressWarnings("unchecked")
	public void getPlayerData() {
		DBObject r = new BasicDBObject("uuid", this.p.getUniqueId().toString());
		DBObject found = MongoDB_players.findOne(r);

		this.HashData.put("uuid", this.p.getUniqueId().toString());

		if (found == null) {
			getDefaultData();
			return;
		}

		this.HashData.putAll(found.toMap());
		this.jedis.hmset("PlayerData:" + p.getName(), this.HashData);
	}

	private void getDefaultData() {
		this.jedis.hmset("PlayerData:" + p.getName(), this.HashData);
	}

	public void setPlayerData() {
		this.HashData = (HashMap<String, String>) this.jedis.hgetAll("PlayerData:" + p.getName());
		this.HashData.put("uuid", this.p.getUniqueId().toString());

		DBObject r = new BasicDBObject("uuid", this.p.getUniqueId().toString());
		DBObject found = MongoDB_players.findOne(r);

		if (found == null) {
			r.putAll(this.HashData);

			MongoDB_players.insert(r);
		} else {
			found.putAll(this.HashData);

			MongoDB_players.save(found);
		}

	}

}
