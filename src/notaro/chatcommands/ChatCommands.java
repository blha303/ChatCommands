package notaro.chatcommands;

import java.io.File;
import java.util.ArrayList;

import notaro.chatcommands.commands.*;
import notaro.chatcommands.files.*;
import notaro.chatcommands.listeners.*;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatCommands extends JavaPlugin{

	public Log log;
	public ArrayList<String> enabledPlayers;
	public UpdateChecker updateChecker;
	public HideFile HiddenPlayers;
	public TpBlockFile TpBlockPlayers;
	public UpdateCheckerFile UpdateTrueOrFalse;

	public void onEnable(){

		(new File(this.getDataFolder().getAbsolutePath())).mkdirs();
		this.HiddenPlayers = new HideFile(new File(this.getDataFolder().getAbsolutePath() + File.separator + "HiddenPlayers.txt"));
		this.HiddenPlayers.loadData();

		(new File(this.getDataFolder().getAbsolutePath())).mkdirs();
		this.TpBlockPlayers = new TpBlockFile(new File(this.getDataFolder().getAbsolutePath() + File.separator + "TpBlockPlayers.txt"));
		this.TpBlockPlayers.loadData();


		(new File(this.getDataFolder().getAbsolutePath())).mkdirs();
		this.UpdateTrueOrFalse = new UpdateCheckerFile(new File(this.getDataFolder().getAbsolutePath() + File.separator + "UpdateChecker.txt"));
		this.UpdateTrueOrFalse.loadData();

		this.getWarpData().loadData();
		this.log = new Log(this);
		this.enabledPlayers = new ArrayList<String>();
		this.registerEvents(this);
		this.RegisterCommands(this);
		this.UpdateChecker(this);

	}

	public void onDisable(){

		this.getWarpData().saveData();
		this.HiddenPlayers.saveData();
		this.TpBlockPlayers.saveData();
		this.UpdateTrueOrFalse.saveData();

	}

	private void RegisterCommands(ChatCommands plugin){

		getCommand("afk").setExecutor(new AFK());
		getCommand("bitchslap").setExecutor(new Bitchslap());
		getCommand("blind").setExecutor(new Blind());
		getCommand("unblind").setExecutor(new Blind());
		getCommand("clear").setExecutor(new Clear());
		getCommand("fakeop").setExecutor(new FakeOP());
		getCommand("hug").setExecutor(new Hug());
		getCommand("join").setExecutor(new Join());
		getCommand("leave").setExecutor(new Leave());
		getCommand("lll").setExecutor(new LLL());
		getCommand("sober").setExecutor(new LLL());
		getCommand("name").setExecutor(new Name());
		getCommand("position").setExecutor(new Position());
		getCommand("say").setExecutor(new Say());
		getCommand("inv").setExecutor(new Inv());
		getCommand("superpick").setExecutor(new Superpick());
		getCommand("boomstick").setExecutor(new Boomstick());
		getCommand("heal").setExecutor(new Heal());
		getCommand("m").setExecutor(new MSG());
		getCommand("setspawn").setExecutor(new Spawn());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("0").setExecutor(new Gamemode());
		getCommand("1").setExecutor(new Gamemode());
		getCommand("2").setExecutor(new Gamemode());
		getCommand("sm").setExecutor(new Mob());
		getCommand("ban").setExecutor(new Ban());
		getCommand("kick").setExecutor(new Kick());
		getCommand("satan").setExecutor(new Satan());
		getCommand("ea").setExecutor(new ExplodingArrowsExecutor(this));
		getCommand("killall").setExecutor(new KillAll());
		getCommand("invis").setExecutor(new Invis());
		getCommand("vis").setExecutor(new Invis());
		getCommand("time").setExecutor(new Time());
		getCommand("tp").setExecutor(new Teleport(this));
		getCommand("summon").setExecutor(new Teleport(this));
		getCommand("hide").setExecutor(new Hide(this));
		getCommand("show").setExecutor(new Hide(this));
		getCommand("tpblock").setExecutor(new TpBlock(this));
		getCommand("weather").setExecutor(new Weather());
		getCommand("lightning").setExecutor(new Lightning());
		getCommand("enderchest").setExecutor(new EnderChest());
		getCommand("info").setExecutor(new Info());
		getCommand("who").setExecutor(new Who());
		getCommand("updatechecker").setExecutor(new UpdateCheckerCommand(this));
		getCommand("kickall").setExecutor(new KickAll());
		getCommand("kill").setExecutor(new Kill());
		getCommand("setwarp").setExecutor(new SetWarp(this));
		getCommand("delwarp").setExecutor(new SetWarp(this));
		getCommand("warp").setExecutor(new Warp(this));
	}

	private void registerEvents(ChatCommands instance){

		PluginManager manager = this.getServer().getPluginManager();

		manager.registerEvents(new ExplodingArrowsListener(this), this);
		manager.registerEvents(new ExplodingArrowsFireListener(this), this);
		manager.registerEvents(new HideListener(this), this);
		manager.registerEvents(new UpdateCheckerListener(this), this);

	}

	private void UpdateChecker(ChatCommands instance){
		this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/server-mods/chatcommands/files.rss");
		if (this.updateChecker.updateChatCommands()){
			if(this.UpdateTrueOrFalse.contains("True")){
				this.log.info("A new version of ChatCommands is out: " +  this.updateChecker.getVersion());
				this.log.info("Get it at: " + this.updateChecker.getLink());
			}if(this.UpdateTrueOrFalse.contains("False")){
				return;
			}
		}
	}
	
	public WarpFile getWarpData(){
		return new WarpFile(this);
	}
}