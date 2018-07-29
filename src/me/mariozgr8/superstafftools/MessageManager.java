package me.mariozgr8.superstafftools;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MessageManager {
	private String prefix;
	private String staffChatPrefix;
	private String noperms;
	private String wrongargs;
	private String playerOnly;
	private String playerOffline;
	private String noPermissionLore;
	
	private String mainInvName;
	private String gamemodeInvName;
    private String difficultyInvName;
	private String weatherInvName;
	private String timeInvName;
	private String effectInvName;
	private String levelEffectInvName;
	private String spawnInvName;
	
	
	private String backItemName;
	
	private String flyItemName;
    private String flyItemLoreEnabled;
    private String flyItemLoreDisabled;
   
    private String godItemName;
    private String godItemLoreEnabled;
    private String godItemLoreDisabled;
   
    private String vanishItemName;
    private String vanishItemLoreEnabled;
    private String vanishItemLoreDisabled;
  
    private String staffModeItemName;
    private String staffModeItemLoreEnabled;
    private String staffModeItemLoreDisabled;
   
    private String healItemName;
    private String feedItemName;
    private String clearChatItemName;
    private String muteChatItemName;
   
    private String gamemodeItemName;
    private String gamemodeItemSurvivalName;
    private String gamemodeItemCreativeName;
    private String gamemodeItemAdventureName;
    private String gamemodeItemSpectatorName;
  
    private String difficultyItemName;
    private String difficultyItemPeacefullName;
    private String difficultyItemEasyName;
    private String difficultyItemNormalName;
    private String difficultyItemHardName;
   
    private String weatherItemName;
    private String weatherRainItemName;
    private String weatherDryItemName;
  
    private String whitelistItemName;
    private String whitelistItemLoreEnabled;
    private String whitelistItemLoreDisabled;
    
    private String timeItemName;
    private String setTimeItemName;
    
    private String effectItemName;
    private String speedItemName;
    private String strenghtItemName;
    private String jumpBoostItemName;
    private String fireResistanceItemName;
    private String resistanceItemName;
    private String regenerationItemName;
    private String hasteItemName;
    private String waterBreathingItemName;
    private String invisibilityItemName;
    private String levitationItemName;
    private String nightVisionItemName;
    private String absorbtionItemName;
    private String glowingItemName;
    private String instantHeaItemName;
    private String clearEffectsItemName;
   
    private String spawnItemName;
    private String closeInvItemName;
   
    private String compassItemName;
    private String compassItemLore;
    
    private String teleportItemName;
    private String teleportItemLore;
    
    private String freezeItemName;
    private String freezeItemLore;
    
    private String inventoryItemName;
    private String inventoryItemLore;
    
    private String enderchestItemName;
    private String enderchestItemLore;
    
    private String informationsItemName;
    private String informationsItemLore;
    
    private String cpsItemName;
    private String cpsItemLore;
    
    private String disableItemName;
    
    private String flyModeOn;
    private String flyModeOff;
    
    private String godModeOn;
    private String godModeOff;
   
    private String vanishModeOn;
    private String vanishModeOff;
    
    private String staffModeOn;
    private String staffModeOff;
    
    private String healMessage;
    private String feedMessage;
    
    private String clearChatMessageSender;
    private String clearChatMessagePlayers;
    
    private String muteChatMessageSenderEnabled;
    private String muteChatMessagePlayersEnabled;
    private String muteChatMessageSenderDisabled;
    private String muteChatMessagePlayersDisabled;
    private String muteChatMessageAction;
   
    private String gamemodeToSurvival;
    private String gamemodeToCreative;
    private String gamemodeToAdventure;
    private String gamemodeToSpectator;
    
    private String difficultyToPeacefull;
    private String difficultyToEasy;
    private String difficultyToNormal;
    private String difficultyToHard;
   
    private String weatherToRain;
    private String weatherToDry;
    
    private String timeChangeMessage;
    
    private String whitelistToOn;
    private String whitelistToOff;
    
    private String clearEffects;
    private String applyEffect;
    
    private String spawnMobMessage;
    
    private String staffModeNoDrop;
    private String staffModeNoBuild;
    private String staffModeNoPickup;
    private String staffModeNoBreak;
    
    private String cannotTeleportMessage;
    
	private String playerUnfreezeMessage;
	private String playerFreezeMessage;
	private String staffUnfreezePlayerMessage;
	private String staffFreezePlayerMessage;
    
    private int cpsTestTime;
    
    public void setup(Plugin p)
	{
    	this.cpsTestTime = p.getConfig().getInt("settings.cps-test-time");
    	
	    this.prefix = p.getConfig().getString("messages.prefix");
	    this.staffChatPrefix = p.getConfig().getString("messages.staffchat-prefix");
	    this.noperms = p.getConfig().getString("messages.noperms");
	    this.wrongargs = p.getConfig().getString("messages.wrongargs");
	    this.playerOnly = p.getConfig().getString("messages.playeronly");
	    this.playerOffline = p.getConfig().getString("messages.playeroffline");
	    
	    this.mainInvName = p.getConfig().getString("inventory.main.name");
	    this.gamemodeInvName = p.getConfig().getString("inventory.gamemode.name");
	    this.difficultyInvName = p.getConfig().getString("inventory.difficulty.name");
	    this.weatherInvName = p.getConfig().getString("inventory.weather.name");
	    this.timeInvName = p.getConfig().getString("inventory.time.name");
	    this.effectInvName = p.getConfig().getString("inventory.effect.name");
	    this.levelEffectInvName = p.getConfig().getString("inventory.levels.name");
	    this.spawnInvName = p.getConfig().getString("inventory.spawn.name");
	    this.noPermissionLore = p.getConfig().getString("inventory.no-permission-lore");
	    
	    this.backItemName = p.getConfig().getString("inventory.main.backItem.name");
	    
	    this.flyItemName = p.getConfig().getString("inventory.main.flyItem.name");
	    this.flyItemLoreEnabled = p.getConfig().getString("inventory.main.flyItem.lore-enabled");
	    this.flyItemLoreDisabled = p.getConfig().getString("inventory.main.flyItem.lore-disabled");
	    
	    this.godItemName = p.getConfig().getString("inventory.main.godItem.name");
	    this.godItemLoreEnabled = p.getConfig().getString("inventory.main.godItem.lore-enabled");
	    this.godItemLoreDisabled = p.getConfig().getString("inventory.main.godItem.lore-disabled");
	    
	    this.vanishItemName = p.getConfig().getString("inventory.main.vanishItem.name");
	    this.vanishItemLoreEnabled = p.getConfig().getString("inventory.main.vanishItem.lore-enabled");
	    this.vanishItemLoreDisabled = p.getConfig().getString("inventory.main.vanishItem.lore-disabled");
	    
	    this.staffModeItemName = p.getConfig().getString("inventory.main.staffModeItem.name");
	    this.staffModeItemLoreEnabled = p.getConfig().getString("inventory.main.staffModeItem.lore-enabled");
	    this.staffModeItemLoreDisabled = p.getConfig().getString("inventory.main.staffModeItem.lore-disabled");
	    
	    this.healItemName = p.getConfig().getString("inventory.main.healItem.name");
	    this.feedItemName = p.getConfig().getString("inventory.main.feedItem.name");
	    this.clearChatItemName = p.getConfig().getString("inventory.main.clearChatItem.name");
	    this.muteChatItemName = p.getConfig().getString("inventory.main.muteChatItem.name");
	    
	    this.gamemodeItemName = p.getConfig().getString("inventory.main.gamemodeItem.name");
	    this.gamemodeItemSurvivalName = p.getConfig().getString("inventory.gamemode.survivalItem.name");
	    this.gamemodeItemCreativeName = p.getConfig().getString("inventory.gamemode.creativeItem.name");
	    this.gamemodeItemAdventureName = p.getConfig().getString("inventory.gamemode.adventureItem.name");
	    this.gamemodeItemSpectatorName = p.getConfig().getString("inventory.gamemode.spectatorItem.name");
	    
	    this.difficultyItemName = p.getConfig().getString("inventory.main.difficultyItem.name");
	    this.difficultyItemPeacefullName = p.getConfig().getString("inventory.difficulty.peacefullItem.name");
	    this.difficultyItemEasyName = p.getConfig().getString("inventory.difficulty.easyItem.name");
	    this.difficultyItemNormalName = p.getConfig().getString("inventory.difficulty.normalItem.name");
	    this.difficultyItemHardName = p.getConfig().getString("inventory.difficulty.hardItem.name");
	    
	    this.weatherItemName = p.getConfig().getString("inventory.main.weatherItem.name");
	    this.weatherDryItemName = p.getConfig().getString("inventory.weather.dryItem.name");
	    this.weatherRainItemName = p.getConfig().getString("inventory.weather.rainItem.name");
	    
	    this.timeItemName = p.getConfig().getString("inventory.main.timeItem.name");
	    this.setTimeItemName = p.getConfig().getString("inventory.time.timeItem.name");
	    
	    this.effectItemName = p.getConfig().getString("inventory.main.effectItem.name");
	    this.speedItemName = p.getConfig().getString("inventory.effect.speedItem.name");
	    this.strenghtItemName = p.getConfig().getString("inventory.effect.strenghtItem.name");
	    this.jumpBoostItemName = p.getConfig().getString("inventory.effect.jumpBoostItem.name");
	    this.fireResistanceItemName = p.getConfig().getString("inventory.effect.fireResistanceItem.name");
	    this.resistanceItemName = p.getConfig().getString("inventory.effect.resistanceItem.name");
	    this.regenerationItemName = p.getConfig().getString("inventory.effect.regenerationItem.name");
	    this.hasteItemName = p.getConfig().getString("inventory.effect.hasteItem.name");
	    this.waterBreathingItemName = p.getConfig().getString("inventory.effect.waterBreathingItem.name");
	    this.invisibilityItemName = p.getConfig().getString("inventory.effect.invisibilityItem.name");
	    this.levitationItemName = p.getConfig().getString("inventory.effect.levitationItem.name");
	    this.nightVisionItemName = p.getConfig().getString("inventory.effect.nightVisionItem.name");
	    this.absorbtionItemName = p.getConfig().getString("inventory.effect.absorbtionItem.name");
	    this.glowingItemName = p.getConfig().getString("inventory.effect.glowingItem.name");
	    this.instantHeaItemName = p.getConfig().getString("inventory.effect.healItem.name");
	    this.clearEffectsItemName = p.getConfig().getString("inventory.effect.clearEffectsItem.name");
	    
	    this.whitelistItemName = p.getConfig().getString("inventory.main.whitelistItem.name");
	    this.whitelistItemLoreEnabled = p.getConfig().getString("inventory.main.whitelistItem.lore-enabled");
	    this.whitelistItemLoreDisabled = p.getConfig().getString("inventory.main.whitelistItem.lore-disabled");
	    
	    this.spawnItemName = p.getConfig().getString("inventory.main.spawnItem.name");
	    
	    this.closeInvItemName = p.getConfig().getString("inventory.main.closeInvItem.name");
	    
	    this.compassItemName = p.getConfig().getString("inventory.staffMode.compassItem.name");
	    this.compassItemLore = p.getConfig().getString("inventory.staffMode.compassItem.lore");
	    
	    this.teleportItemName = p.getConfig().getString("inventory.staffMode.randomTeleport.name");
	    this.teleportItemLore = p.getConfig().getString("inventory.staffMode.randomTeleport.lore");
	    
	    this.freezeItemName = p.getConfig().getString("inventory.staffMode.freezeItem.name");
	    this.freezeItemLore = p.getConfig().getString("inventory.staffMode.freezeItem.lore");
	    
	    this.inventoryItemName = p.getConfig().getString("inventory.staffMode.inventoryItem.name");
	    this.inventoryItemLore = p.getConfig().getString("inventory.staffMode.inventoryItem.lore");
	    
	    this.enderchestItemName = p.getConfig().getString("inventory.staffMode.enderChestItem.name");
	    this.enderchestItemLore = p.getConfig().getString("inventory.staffMode.enderChestItem.lore");
	    
	    this.informationsItemName = p.getConfig().getString("inventory.staffMode.informationsItem.name");
	    this.informationsItemLore = p.getConfig().getString("inventory.staffMode.informationsItem.lore");
	    
	    this.cpsItemName = p.getConfig().getString("inventory.staffMode.cpsItem.name");
	    this.cpsItemLore = p.getConfig().getString("inventory.staffMode.cpsItem.lore");
	    
	    this.disableItemName = p.getConfig().getString("inventory.staffMode.disableItem.name");
	    
	    this.flyModeOn = p.getConfig().getString("messages.fly-mode-on");
	    this.flyModeOff = p.getConfig().getString("messages.fly-mode-off");
	    
	    this.godModeOn = p.getConfig().getString("messages.god-mode-on");
	    this.godModeOff = p.getConfig().getString("messages.god-mode-off");
	    
	    this.vanishModeOn = p.getConfig().getString("messages.vanish-mode-on");
	    this.vanishModeOff = p.getConfig().getString("messages.vanish-mode-off");
	    
	    this.staffModeOn = p.getConfig().getString("messages.staffMode-on");
	    this.staffModeOff = p.getConfig().getString("messages.staffMode-off");
	    
	    this.healMessage = p.getConfig().getString("messages.heal-message");
	    this.feedMessage = p.getConfig().getString("messages.feed-message");
	    
	    this.clearChatMessageSender = p.getConfig().getString("messages.clearChat-message-sender");
	    this.clearChatMessagePlayers = p.getConfig().getString("messages.clearChat-message-players");
	    
	    this.muteChatMessageSenderEnabled = p.getConfig().getString("messages.muteChat-message-sender-enabled");
	    this.muteChatMessagePlayersEnabled = p.getConfig().getString("messages.muteChat-message-players-enabled");
	    this.muteChatMessageSenderDisabled = p.getConfig().getString("messages.muteChat-message-sender-disabled");
	    this.muteChatMessagePlayersDisabled = p.getConfig().getString("messages.muteChat-message-players-disabled");
	    this.muteChatMessageAction = p.getConfig().getString("messages.muteChat-message-chatAction");
	    
	    this.gamemodeToSurvival = p.getConfig().getString("messages.gamemode-message-survival");
	    this.gamemodeToCreative = p.getConfig().getString("messages.gamemode-message-creative");
	    this.gamemodeToAdventure = p.getConfig().getString("messages.gamemode-message-adventure");
	    this.gamemodeToSpectator = p.getConfig().getString("messages.gamemode-message-spectator");
	    
	    this.difficultyToPeacefull = p.getConfig().getString("messages.difficulty-message-peacefull");
	    this.difficultyToEasy = p.getConfig().getString("messages.difficulty-message-easy");
	    this.difficultyToNormal = p.getConfig().getString("messages.difficulty-message-normal");
	    this.difficultyToHard = p.getConfig().getString("messages.difficulty-message-hard");
	    
	    this.weatherToRain = p.getConfig().getString("messages.weather-rain");
	    this.weatherToDry = p.getConfig().getString("messages.weather-dry");
	    
	    this.timeChangeMessage = p.getConfig().getString("messages.timechange-message");
	    
	    this.whitelistToOn = p.getConfig().getString("messages.whitelist-on");
	    this.whitelistToOff = p.getConfig().getString("messages.whitelist-off");
	    
	    this.clearEffects = p.getConfig().getString("messages.effect-message-clearEffects");
	    this.applyEffect = p.getConfig().getString("messages.effect-message-applyEffect");
	    
	    this.spawnMobMessage = p.getConfig().getString("messages.spawn-mob-message");
	    
	    this.staffModeNoBreak = p.getConfig().getString("messages.staffmode-prevent-break");
	    this.staffModeNoBuild = p.getConfig().getString("messages.staffmode-prevent-build");
	    this.staffModeNoDrop = p.getConfig().getString("messages.staffmode-prevent-drop");
	    this.staffModeNoPickup = p.getConfig().getString("messages.staffmode-prevent-pickup");
	    
	    this.cannotTeleportMessage = p.getConfig().getString("messages.no-random-teleport");
	    
		this.playerUnfreezeMessage = p.getConfig().getString("messages.player-unfreeze");
		this.playerFreezeMessage = p.getConfig().getString("messages.player-freeze");
		this.staffUnfreezePlayerMessage = p.getConfig().getString("messages.staff-unfreeze-player");
		this.staffFreezePlayerMessage = p.getConfig().getString("messages.staff-freeze-player");
	  }
	  public int getCpsTestTime() {
		  return cpsTestTime;
	  }
	  public String getMainInvName()
	  {
	    String name = setColorForMessage(this.mainInvName);
	    return name;
	  }
	  
	  public String getGamemodeInvName()
	  {
	    return setColorForMessage(this.gamemodeInvName);
	  }
	  
	  public String getDifficultyInvName()
	  {
	    return setColorForMessage(this.difficultyInvName);
	  }
	  
	  public String getWeatherInvName()
	  {
	    return setColorForMessage(this.weatherInvName);
	  }
	  
	  public String getTimeInvName()
	  {
	    return setColorForMessage(this.timeInvName);
	  }
	  
	  public String getEffectInvName()
	  {
	    return setColorForMessage(this.effectInvName);
	  }
	  
	  public String getLevelEffectInvName()
	  {
	    return setColorForMessage(this.levelEffectInvName);
	  }
	  
	  public String getSpawnInvName()
	  {
	    return setColorForMessage(this.spawnInvName);
	  }
	  
	  public String getNoPermissionLore()
	  {
	    return this.noPermissionLore;
	  }
	  
	  public String getBackItemName()
	  {
	    return this.backItemName;
	  }
	  
	  public String getFlyItemName()
	  {
	    return this.flyItemName;
	  }
	  
	  public String getFlyItemLoreEnabled()
	  {
	    return this.flyItemLoreEnabled;
	  }
	  
	  public String getFlyItemLoreDisabled()
	  {
	    return this.flyItemLoreDisabled;
	  }
	  
	  public String getGodItemName()
	  {
	    return this.godItemName;
	  }
	  
	  public String getGodItemLoreEnabled()
	  {
	    return this.godItemLoreEnabled;
	  }
	  
	  public String getGodItemLoreDisabled()
	  {
	    return this.godItemLoreDisabled;
	  }
	  
	  public String getVanishItemName()
	  {
	    return this.vanishItemName;
	  }
	  
	  public String getVanishItemLoreEnabled()
	  {
	    return this.vanishItemLoreEnabled;
	  }
	  
	  public String getVanishItemLoreDisabled()
	  {
	    return this.vanishItemLoreDisabled;
	  }
	  
	  public String getCloseInvItemName()
	  {
	    return this.closeInvItemName;
	  }
	  
	  public String getFlyModeOn()
	  {
	    return this.flyModeOn;
	  }
	  
	  public String getFlyModeOff()
	  {
	    return this.flyModeOff;
	  }
	  
	  public String getGodModeOn()
	  {
	    return this.godModeOn;
	  }
	  
	  public String getGodModeOff()
	  {
	    return this.godModeOff;
	  }
	  
	  public String getHealItemName()
	  {
	    return this.healItemName;
	  }
	  
	  public String getFeedItemName()
	  {
	    return this.feedItemName;
	  }
	  
	  public String getHealMessage()
	  {
	    return this.healMessage;
	  }
	  
	  public String getFeedMessage()
	  {
	    return this.feedMessage;
	  }
	  
	  public String getClearChatItemName()
	  {
	    return this.clearChatItemName;
	  }
	  
	  public String getClearChatMessageSender()
	  {
	    return this.clearChatMessageSender;
	  }
	  
	  public String getClearChatMessagePlayers()
	  {
	    return this.clearChatMessagePlayers;
	  }
	  
	  public String getMuteChatItemName()
	  {
	    return this.muteChatItemName;
	  }
	  
	  public String getMuteChatMessageSenderEnabled()
	  {
	    return this.muteChatMessageSenderEnabled;
	  }
	  
	  public String getMuteChatMessagePlayersEnabled()
	  {
	    return this.muteChatMessagePlayersEnabled;
	  }
	  
	  public String getMuteChatMessageSenderDisabled()
	  {
	    return this.muteChatMessageSenderDisabled;
	  }
	  
	  public String getMuteChatMessagePlayersDisabled()
	  {
	    return this.muteChatMessagePlayersDisabled;
	  }
	  
	  public String getMuteChatMessageAction()
	  {
	    return this.muteChatMessageAction;
	  }
	  
	  public String getGamemodeItemName()
	  {
	    return this.gamemodeItemName;
	  }
	  
	  public String getGamemodeItemSurvivalName()
	  {
	    return this.gamemodeItemSurvivalName;
	  }
	  
	  public String getGamemodeItemCreativeName()
	  {
	    return this.gamemodeItemCreativeName;
	  }
	  
	  public String getGamemodeItemAdventureName()
	  {
	    return this.gamemodeItemAdventureName;
	  }
	  
	  public String getGamemodeItemSpectatorName()
	  {
	    return this.gamemodeItemSpectatorName;
	  }
	  
	  public String getGamemodeToSurvival()
	  {
	    return this.gamemodeToSurvival;
	  }
	  
	  public String getGamemodeToCreative()
	  {
	    return this.gamemodeToCreative;
	  }
	  
	  public String getGamemodeToAdventure()
	  {
	    return this.gamemodeToAdventure;
	  }
	  
	  public String getGamemodeToSpectator()
	  {
	    return this.gamemodeToSpectator;
	  }
	  
	  public String getDifficultyItemName()
	  {
	    return this.difficultyItemName;
	  }
	  
	  public String getDifficultyItemPeacefullName()
	  {
	    return this.difficultyItemPeacefullName;
	  }
	  
	  public String getDifficultyItemEasyName()
	  {
	    return this.difficultyItemEasyName;
	  }
	  
	  public String getDifficultyItemNormalName()
	  {
	    return this.difficultyItemNormalName;
	  }
	  
	  public String getDifficultyItemHardName()
	  {
	    return this.difficultyItemHardName;
	  }
	  
	  public String getDifficultyToPeacefull()
	  {
	    return this.difficultyToPeacefull;
	  }
	  
	  public String getDifficultyToEasy()
	  {
	    return this.difficultyToEasy;
	  }
	  
	  public String getDifficultyToNormal()
	  {
	    return this.difficultyToNormal;
	  }
	  
	  public String getDifficultyToHard()
	  {
	    return this.difficultyToHard;
	  }
	  
	  public String getWeatherItemName()
	  {
	    return this.weatherItemName;
	  }
	  
	  public String getTimeItemName()
	  {
	    return this.timeItemName;
	  }
	  
	  public String getWhitelistItemName()
	  {
	    return this.whitelistItemName;
	  }
	  
	  public String getEffectItemName()
	  {
	    return this.effectItemName;
	  }
	  
	  public String getStaffModeItemName()
	  {
	    return this.staffModeItemName;
	  }
	  
	  public String getStaffModeItemLoreEnabled()
	  {
	    return this.staffModeItemLoreEnabled;
	  }
	  
	  public String getStaffModeItemLoreDisabled()
	  {
	    return this.staffModeItemLoreDisabled;
	  }
	  
	  public String getWeatherRainItemName()
	  {
	    return this.weatherRainItemName;
	  }
	  
	  public String getWeatherDryItemName()
	  {
	    return this.weatherDryItemName;
	  }
	  
	  public String getWeatherToRain()
	  {
	    return this.weatherToRain;
	  }
	  
	  public String getWeatherToDry()
	  {
	    return this.weatherToDry;
	  }
	  
	  public String getWhitelistItemLoreEnabled()
	  {
	    return this.whitelistItemLoreEnabled;
	  }
	  
	  public String getWhitelistItemLoreDisabled()
	  {
	    return this.whitelistItemLoreDisabled;
	  }
	  
	  public String getWhitelistToOn()
	  {
	    return this.whitelistToOn;
	  }
	  
	  public String getWhitelistToOff()
	  {
	    return this.whitelistToOff;
	  }
	  
	  public String getVanishModeOn()
	  {
	    return this.vanishModeOn;
	  }
	  
	  public String getVanishModeOff()
	  {
	    return this.vanishModeOff;
	  }
	  
	  public String getStaffChatPrefix()
	  {
	    return this.staffChatPrefix;
	  }
	  
	  public String getSetTimeItemName()
	  {
	    return this.setTimeItemName;
	  }
	  
	  public String getTimeChangeMessage()
	  {
	    return this.timeChangeMessage;
	  }
	  
	  public String getStaffModeOn()
	  {
	    return this.staffModeOn;
	  }
	  
	  public String getStaffModeOff()
	  {
	    return this.staffModeOff;
	  }
	  
	  public String getSpeedItemName()
	  {
	    return this.speedItemName;
	  }
	  
	  public String getStrenghtItemName()
	  {
	    return this.strenghtItemName;
	  }
	  
	  public String getJumpBoostItemName()
	  {
	    return this.jumpBoostItemName;
	  }
	  
	  public String getFireResistanceItemName()
	  {
	    return this.fireResistanceItemName;
	  }
	  
	  public String getResistanceItemName()
	  {
	    return this.resistanceItemName;
	  }
	  
	  public String getRegenerationItemName()
	  {
	    return this.regenerationItemName;
	  }
	  
	  public String getHasteItemName()
	  {
	    return this.hasteItemName;
	  }
	  
	  public String getWaterBreathingItemName()
	  {
	    return this.waterBreathingItemName;
	  }
	  
	  public String getInvisibilityItemName()
	  {
	    return this.invisibilityItemName;
	  }
	  
	  public String getLevitationItemName()
	  {
	    return this.levitationItemName;
	  }
	  
	  public String getNightVisionItemName()
	  {
	    return this.nightVisionItemName;
	  }
	  
	  public String getAbsorbtionItemName()
	  {
	    return this.absorbtionItemName;
	  }
	  
	  public String getGlowingItemName()
	  {
	    return this.glowingItemName;
	  }
	  
	  public String getInstantHeaItemName()
	  {
	    return this.instantHeaItemName;
	  }
	  
	  public String getClearEffectsItemName()
	  {
	    return this.clearEffectsItemName;
	  }
	  
	  public String getClearEffects()
	  {
	    return this.clearEffects;
	  }
	  
	  public String getSpawnItemName()
	  {
	    return this.spawnItemName;
	  }
	  
	  public String getSpawnMobMessage()
	  {
	    return this.spawnMobMessage;  
	  }
	  
	  public String getCompassItemName() {
		return compassItemName;
	  }

	  public String getCompassItemLore() {
		return compassItemLore;
	  }

	  public String getTeleportItemName() {
		return teleportItemName;
	  }

	  public String getTeleportItemLore() {
		return teleportItemLore;
	  }
	  public String getFreezeItemName() {
		  return freezeItemName;
	  }
	  public String getFreezeItemLore() {
		  return freezeItemLore;
	  }
	  public String getInventoryItemName() {
		  return inventoryItemName;
	  }
	  public String getInventoryItemLore() {
		  return inventoryItemLore;
	  }
	  public String getEnderChestItemName() {
		  return enderchestItemName;
	  }
	  public String getEnderChestItemLore() {
		  return enderchestItemLore;
	  }
	  public String getInformationsItemName() {
		  return informationsItemName;
	  }
	  public String getInformationsItemLore() {
		  return informationsItemLore;
	  }
	  public String getCpsItemName() {
		  return cpsItemName;
	  }
	  public String getCpsItemLore() {
		  return cpsItemLore;
	  }
	  public String getDisableItemName() {
		  return disableItemName;
	  }
	  public String getStaffModeNoDrop() {
		  return staffModeNoDrop;
	  }
	  public String getStaffModeNoPickup() {
		  return staffModeNoPickup;
	  }
	  public String getStaffModeNoBuild() {
		  return staffModeNoBuild;
	  }
	  public String getStaffModeNoBreak() {
		  return staffModeNoBreak;
	  }
	  public String getCannotTeleportMessage() {
		  return cannotTeleportMessage;
	  }
	  public String getPlayerUnfreezeMessage() {
	      return playerUnfreezeMessage;
	  }
	  public String getPlayerFreezeMessage() {
		  return playerFreezeMessage;
	  }
	  public String getStaffUnfreezePlayerMessage() {
	  	  return staffUnfreezePlayerMessage;
	  }
	  public String getStaffFreezePlayerMessage() {
	   	  return staffFreezePlayerMessage;
	  }

	  public String getApplyEffect(String potionName, int level)
	  {
	    if (level == 1)
	    {
	      String result = this.applyEffect.replace("%potion%", potionName).replace("%level%", "");
	      return result;
	    }
	    String result = this.applyEffect.replace("%potion%", potionName).replace("%level%", String.valueOf(level));
	    return result;
	  }
	  
	  public String setColorForMessage(String message)
	  {
	    message = ChatColor.translateAlternateColorCodes('&', message);
	    return message;
	  }
	  
	  public String getPrefix()
	  {
	    return this.prefix;
	  }
	  
	  public void sendMessageToPlayer(String message, Player p)
	  {
	    p.sendMessage(setColorForMessage(this.prefix + message));
	  }
	  public void sendMessageToPlayerWithoutPrefix(String message, Player p) {
		  p.sendMessage(setColorForMessage(message));
	  }
	  
	  public void playerOnly(CommandSender sender)
	  {
	    sender.sendMessage(this.prefix + ChatColor.stripColor(this.setColorForMessage(this.playerOnly)));
	  }
	  
	  public void noPermission(Player p)
	  {
	    p.sendMessage(setColorForMessage(this.prefix + this.noperms));
	  }
	  
	  public void wrongArgs(Player p)
	  {
	    p.sendMessage(setColorForMessage(this.prefix + this.wrongargs));
	  }
	  
	  public void playerOffline(Player p, String playerOfflineName)
	  {
	    p.sendMessage(setColorForMessage(this.prefix + this.playerOffline.replace("%player%", playerOfflineName)));
	  }
}
