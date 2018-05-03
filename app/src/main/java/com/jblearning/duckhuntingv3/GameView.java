package com.jblearning.duckhuntingv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class GameView extends View {
  public static int DELTA_TIME = 100;
  private int [ ] TARGETS = { R.drawable.anim_duck0, R.drawable.anim_duck1,
                            R.drawable.anim_duck2, R.drawable.anim_duck1 };
  private Paint paint;
  private Bitmap [ ] ducks1;
  private Bitmap [ ] ducks2;
  private int duckFrame1;
  private int duckFrame2;

  private Game game;

  public GameView( Context context, int width, int height ) {
    super( context );
    ducks1 = new Bitmap[TARGETS.length];
    ducks2 = new Bitmap[TARGETS.length];
    for( int i = 0; i < ducks1.length; i++ )
      ducks1[i] = BitmapFactory.decodeResource( getResources( ), TARGETS[i] );
    for( int i = 0; i < ducks2.length; i++ )
      ducks2[i] = BitmapFactory.decodeResource( getResources( ), TARGETS[i] );

    float scale1 = ( ( float ) width / ( ducks1[0].getWidth( ) * 5 ) );
    Rect duckRect1 = new Rect( 0, 0, width / 5, ( int ) ( ducks1[0].getHeight( ) * scale1 ) );
    float scale2 = ( ( float ) width / ( ducks2[0].getWidth( ) * 5 ) );
    Rect duckRect2 = new Rect( 0, 0, width / 5, ( int ) ( ducks2[0].getHeight( ) * scale2 ) );

    game = new Game( duckRect1, duckRect2,.03f,.03f, 5,.2f );
    game.setDuckSpeed1( width * .00003f );
    game.setDuckSpeed2( width * .00003f );
    game.setBulletSpeed( width * .0003f );
    game.setDeltaTime( DELTA_TIME );

    game.setHuntingRect( new Rect( 0, 0, width, height ) );
    game.setCannon( new Point( 0, height ), width / 30,
       width / 15, width / 50);

    paint = new Paint( );
    paint.setColor( 0xFF000000 );
    paint.setAntiAlias( true );
    paint.setStrokeWidth( game.getBarrelRadius( ) );
  }

  public void onDraw( Canvas canvas ) {
    super.onDraw( canvas );
    // draw cannon
    canvas.drawCircle( game.getCannonCenter( ).x, game.getCannonCenter( ).y,
         game.getCannonRadius( ), paint );

    // draw cannon barrel
    canvas.drawLine(
        game.getCannonCenter( ).x, game.getCannonCenter( ).y,
        game.getCannonCenter( ).x + game.getBarrelLength( )
            * ( float ) Math.cos( game.getCannonAngle( ) ),
        game.getCannonCenter( ).y - game.getBarrelLength( )
            * ( float ) Math.sin( game.getCannonAngle( ) ),
        paint );

    // draw bullet
    if( ! game.bulletOffScreen( ) )
      canvas.drawCircle( game.getBulletCenter( ).x,
              game.getBulletCenter( ).y, game.getBulletRadius( ), paint );

    // draw animated duck
    duckFrame1 = ( duckFrame1 + 1 ) % ducks1.length;
    if( game.isDuckShot1( ) )
      canvas.drawBitmap( ducks1[0], null,
              game.getDuckRect1( ), paint );
    else
      canvas.drawBitmap( ducks1[duckFrame1], null,
              game.getDuckRect1( ), paint );
    duckFrame2 = ( duckFrame2 + 1 ) % ducks2.length;
    if( game.isDuckShot2( ) )
      canvas.drawBitmap( ducks2[0], null,
              game.getDuckRect2( ), paint );
    else
      canvas.drawBitmap( ducks2[duckFrame2], null,
              game.getDuckRect2( ), paint );
  }

  public Game getGame( ) {
    return game;
  }
}
