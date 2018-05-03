package com.jblearning.duckhuntingv3;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask {
  private Game game;
  private GameView gameView;
  
  public GameTimerTask( GameView view ) {
    gameView = view;
    game = view.getGame( );
    game.startDuckFromRightTopHalf_1( );
    game.startDuckFromRightTopHalf_2( );
  }
  
  public void run( ) {
    game.moveDuck( );
    if( game.bulletOffScreen( ) )
      game.loadBullet( );
    else if( game.isBulletFired( ) )
      game.moveBullet( );
    if( game.duckOffScreen1( ) || game.duckDown1()) {
      game.setDuckShot1( false );
      game.startDuckFromRightTopHalf_1( );
    } else if( game.duckHit1( ) ) {
      game.setDuckShot1( true );
      ( ( MainActivity ) gameView.getContext( ) ).playHitSound( );
      game.loadBullet( );
    }
    if( game.duckOffScreen2( ) || game.duckDown2() ) {
      game.setDuckShot2( false );
      game.startDuckFromRightTopHalf_2( );
    } else if( game.duckHit2( ) ) {
      game.setDuckShot2( true );
      ( ( MainActivity ) gameView.getContext( ) ).playHitSound( );
      game.loadBullet( );
    }
    gameView.postInvalidate( );
  }
}
