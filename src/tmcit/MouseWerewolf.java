package tmcit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aiwolf.common.data.Agent;
import org.aiwolf.common.data.Role;
import org.aiwolf.common.data.Talk;
import org.aiwolf.common.net.GameInfo;
import org.aiwolf.common.net.GameSetting;
import org.aiwolf.sample.lib.AbstractWerewolf;

public class MouseWerewolf extends AbstractWerewolf {

	Agent me;
	GameInfo currentGameInfo;
	List<Agent> aliveList = new ArrayList<>();
	List<Agent> vote_candinate = new ArrayList<>();
	List<Agent> attack_candinate = new ArrayList<>();
	List<Agent> werewolfs = new ArrayList<>();
	Map<Agent,Role> werewolf_condition = new HashMap<>();
	boolean saidCO = false;
	int talkListHead;
	int talkcount;
	int daycount;

	//エージェント名の設定
	public String getName() {
		return "MouseWerewolf";
	}

	//ゲーム情報の更新
	public void update(GameInfo gameInfo) {
		//currentGameInfoを更新
		currentGameInfo = gameInfo;
	}

	//ゲーム情報の初期化
	public void initialize(GameInfo gameInfo, GameSetting gameSetting) {
		//gameInfoから自分の設定を取得する
		me = gameInfo.getAgent();
	}

	//襲撃する相手の選択(agentを返す)
	@Override
	public Agent attack() {
		//もし、襲撃対象がwhisperで決定した場合、その対象を襲撃。その情報を削除
		//決定していない場合、襲撃する相手を決定する関数を呼び出す。
		//aliveList = new ArrayList<>(currentGameInfo.getAliveAgentList());
		//aliveList.remove(me);
		return randomSelect(attack_candinate);
	}

	//日の初めの処理
	@Override
	public void dayStart() {
		//襲撃・吊る候補を取得
		vote_candinate = new ArrayList<>(currentGameInfo.getAliveAgentList());
		attack_candinate = new ArrayList<>(currentGameInfo.getAliveAgentList());
		//人狼の現在の状態を取得
		werewolf_condition = new HashMap<>(currentGameInfo.getRoleMap());
		//人狼のリストを作成
		werewolfs = new ArrayList<>(werewolf_condition.keySet());

		//吊る対象から自分を除去
		vote_candinate.remove(me);
		//襲撃対象から人狼を除去
		attack_candinate.removeAll(werewolfs);

		//日付のカウント
		daycount += 1;
		//トークのターンカウントをリセット
		talkcount = 0;
	}

	//全体での発言処理(stringを返す)
	@Override
	 public String talk() {
		  return Talk.OVER;
	}

	//投票対象の選択(agentを返す)
	public Agent vote() {
		//もし、whisperで吊る対象を決定した場合にはその対象を選択
		//最も村人らしい人物を優先的に襲う。
		aliveList = new ArrayList<>(currentGameInfo.getAliveAgentList());
		aliveList.remove(me);
		return randomSelect(aliveList);
	}

	//人狼同士のみの発言処理(stringを返す)
	@Override
	public String whisper() {
		//人狼間で会話して、襲う・吊る対象を決定する。
		return null;
	}

	//ゲーム終了時の動作
	@Override
	public void finish() {
	}

	//与えられたリストからランダムに誰かを選択する(返り値:null or Agent)
	<T> T randomSelect(List<T> list ) {
		if ( list.isEmpty()) {
			return null;
		}
		else {
			return list.get((int) (Math.random() * list.size()));
		}
	}
}