package tmcit;
import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Player;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;


public class MouseNlpPlayer implements Player {
	private Player player = null;

	public MouseNlpPlayer() {
		player = new tmcit.MousePlayer();
	}

	public Agent attack() {
		return player.attack();
	}

	public void dayStart() {
		player.dayStart();
	}

	public Agent divine() {
		return player.divine();
	}

	public void finish() {
		player.finish();
	}

	public String getName() {
		return "MouseNlpPlayer";
	}

	public Agent guard() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		player.initialize(gameInfo, gameSetting);
		// TODO 自動生成されたメソッド・スタブ

	}

	public String talk() {
		System.out.println("cookie star!");
		return player.talk();
		//return "人狼なんかやめてUNOやろうぜ！";
	}

	public void update(GameInfo gameInfo) {
		player.update(gameInfo);
	}

	public Agent vote() {
		return player.vote();
	}

	public String whisper() {
		return player.whisper();
	}

}
