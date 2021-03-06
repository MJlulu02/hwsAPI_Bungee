package Plugins.hwsAPI.DataManageur;

import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import Plugins.hwsAPI.PluginsManageur.HWSAPI;
import Plugins.hwsAPI.PluginsManageur.Main;
import Plugins.hwsAPI.Utils.HwsGradeAPI;
import net.md_5.bungee.BungeeCord;
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

	@SuppressWarnings("deprecation")
	public void getPlayerData() {
		if(this.jedis.exists("PlayerData:" + p.getName())) {
			this.jedis.expire("PlayerData:" + p.getName(), -1);
			return;
		}
		
		
		DBObject r = new BasicDBObject("uuid", this.p.getUniqueId().toString());
		DBObject found = MongoDB_players.findOne(r);

		this.HashData.put("uuid", this.p.getUniqueId().toString());

		if (found == null) {
			getDefaultData();
			return;
		}

		found.removeField("_id");
		found.removeField("uuid");
		
		if(Main.instance.mMode == true) {
			String PlayerGrade = (String) found.get("hwsrank");
			HwsGradeAPI hwsGradeAPI = HwsGradeAPI.valueOf(PlayerGrade);
			
			  if(hwsGradeAPI.getPower() > 7) {
				  p.disconnect("�cServeur Actuellement en Maintenance !");
				  return;
			  }
		}
		
		for (String s : found.keySet()) {
			this.HashData.put(s, found.get(s).toString());
		}
		this.jedis.hmset("PlayerData:" + p.getName(), this.HashData);
	}

	private void getDefaultData() {
		this.jedis.hmset("PlayerData:" + p.getName(), this.HashData);
	}

	public void setPlayerData() {
		this.HashData = (HashMap<String, String>) this.jedis.hgetAll("PlayerData:" + p.getName());
		this.HashData.put("pseudo", this.p.getName());
		
		DBObject r = new BasicDBObject("uuid", this.p.getUniqueId().toString());
		DBObject found = MongoDB_players.findOne(r);
		
		if (found == null) {
			r.putAll(this.HashData);

			MongoDB_players.insert(r);
		} else {
			found.putAll(this.HashData);
			BungeeCord.getInstance().broadcast("f: "+found.keySet());
			BungeeCord.getInstance().broadcast("f: "+this.HashData.keySet()+" - "+this.jedis.exists("PlayerData:" + p.getName()));
			
			MongoDB_players.save(found);
		}
	}

}
