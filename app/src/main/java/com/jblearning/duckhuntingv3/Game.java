package com.jblearning.duckhuntingv3;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Random;

public class Game {
  private Rect huntingRect;
  private int deltaTime; // in milliSeconds
  
  private Rect duckRect1;
  private int duckWidth1;
  private int duckHeight1;
  private float duckSpeed1;
  private boolean duckShot1;

  private Rect duckRect2;
  private int duckWidth2;
  private int duckHeight2;
  private float duckSpeed2;
  private boolean duckShot2;
  
  private Point cannonCenter; 
  private int cannonRadius;
  private int barrelLength;
  private int barrelRadius;
  private float cannonAngle;

  private Point bulletCenter; 
  private int bulletRadius; 
  private boolean bulletFired;
  private float bulletAngle; 
  private float bulletSpeed;
  
  private Random random;
  
  public Game( Rect newDuckRect1, Rect newDuckRect2,
               float newDuckSpeed1, float newDuckSpeed2,
               int newBulletRadius,float newBulletSpeed ) {
    setDuckRect1( newDuckRect1);
    setDuckRect2( newDuckRect2);
    setDuckSpeed1( newDuckSpeed1);
    setDuckSpeed2( newDuckSpeed2);
    setBulletRadius( newBulletRadius );
    setBulletSpeed( newBulletSpeed ); 
    random = new Random( );
    bulletFired = false;
    duckShot1 = false;
    duckShot2 = false;
    cannonAngle = ( float ) Math.PI / 4; // starting cannon angle
  }
 
  public Rect getHuntingRect( ) {
    return huntingRect;
  }  

  public void setHuntingRect( Rect newHuntingRect ) {
	if( newHuntingRect != null )
      huntingRect = newHuntingRect;
  }
  
  public void setDeltaTime( int newDeltaTime ) {
    if( newDeltaTime > 0 )
      deltaTime = newDeltaTime;
  }
  
  public Rect getDuckRect1( ) {
    return duckRect1;
  }
  public Rect getDuckRect2( ) {
    return duckRect2;
  }

  public void setDuckRect1( Rect newDuckRect ) {
    if( newDuckRect != null ) {
      duckWidth1 = newDuckRect.right - newDuckRect.left;
      duckHeight1 = newDuckRect.bottom - newDuckRect.top;
      duckRect1 = newDuckRect;
    }
  }

  public void setDuckRect2( Rect newDuckRect ) {
    if( newDuckRect != null ) {
      duckWidth2 = newDuckRect.right - newDuckRect.left;
      duckHeight2 = newDuckRect.bottom - newDuckRect.top;
      duckRect2 = newDuckRect;
    }
  }
  
  public void setDuckSpeed1( float newDuckSpeed ) {
    if( newDuckSpeed > 0 )
      duckSpeed1 = newDuckSpeed;
  }
  public void setDuckSpeed2( float newDuckSpeed ) {
    if( newDuckSpeed > 0 )
      duckSpeed2 = newDuckSpeed;
  }

  public Point getCannonCenter( ) {
    return cannonCenter;
  }
  
  public int getCannonRadius( ) {
    return cannonRadius;
  }
  
  public int getBarrelLength( ) {
    return barrelLength;
  }
  
  public int getBarrelRadius( ) {
    return barrelRadius;
  }
  
  public void setCannon( Point newCannonCenter, int newCannonRadius,
                         int newBarrelLength, int newBarrelRadius ) {
    if( newCannonCenter != null && newCannonRadius > 0 
    		                    && newBarrelLength > 0 ) {
      cannonCenter = newCannonCenter;
      cannonRadius = newCannonRadius;
      barrelLength = newBarrelLength;
      barrelRadius = newBarrelRadius;
      bulletCenter = new Point( 
      ( int ) ( cannonCenter.x + cannonRadius * Math.cos( cannonAngle ) ), 
      ( int ) ( cannonCenter.y - cannonRadius * Math.sin( cannonAngle ) ) );
    }
  }
  
  public Point getBulletCenter( ) {
    return bulletCenter;
  }
  
  public int getBulletRadius( ) {
    return bulletRadius;
  }

  public void setBulletRadius( int newBulletRadius ) {
    if( newBulletRadius > 0 )
      bulletRadius = newBulletRadius;
  }
  
  public void setBulletSpeed( float newBulletSpeed ) {
    if( newBulletSpeed > 0 )
      bulletSpeed = newBulletSpeed;
  }
  
  public float getCannonAngle( ) {
    return cannonAngle;
  }

  public void setCannonAngle( float newCannonAngle ) {
    if( newCannonAngle >= 0 && newCannonAngle <= Math.PI / 2 )
      cannonAngle = newCannonAngle;
    else if( newCannonAngle < 0 )
      cannonAngle = 0;
    else
      cannonAngle = ( float ) Math.PI / 2;
    if( !isBulletFired( ) )
      loadBullet( );
  }
  
  public boolean isBulletFired( ) {
    return bulletFired;
  }
  
  public void fireBullet( ) {
    bulletFired = true;
    bulletAngle = cannonAngle;
  }
  
  public boolean isDuckShot1( ) {
    return duckShot1;
  }
  public boolean isDuckShot2( ) {
    return duckShot2;
  }

  public void setDuckShot1( boolean newDuckShot ) {
    duckShot1 = newDuckShot;
  }
  public void setDuckShot2( boolean newDuckShot ) {
    duckShot2 = newDuckShot;
  }

  public void startDuckFromRightTopHalf_1( ) {
    duckRect1.left = huntingRect.right;
    duckRect1.right = duckRect1.left + duckWidth1;
    duckRect1.top = random.nextInt( huntingRect.bottom / 2 );
    duckRect1.bottom = duckRect1.top + duckHeight1;
  }

  public void startDuckFromRightTopHalf_2(){
    duckRect2.left = huntingRect.right;
    duckRect2.right = duckRect2.left + duckWidth2;
    duckRect2.top = random.nextInt( huntingRect.bottom / 2 );
    duckRect2.bottom = duckRect2.top + duckHeight2;
  }
  
  public void moveDuck( ) {
    if( !duckShot1 ) { // move left
      duckRect1.left -= duckSpeed1 * deltaTime;
      duckRect1.right -= duckSpeed1 * deltaTime;
    } else { // move down
      duckRect1.top += 5 * duckSpeed1 * deltaTime;
      duckRect1.bottom += 5 * duckSpeed1 * deltaTime;
    }
    if( !duckShot2 ) { // move left
      duckRect2.left -= duckSpeed2 * deltaTime;
      duckRect2.right -= duckSpeed2 * deltaTime;
    } else { // move down
      duckRect2.top += 5 * duckSpeed2 * deltaTime;
      duckRect2.bottom += 5 * duckSpeed2 * deltaTime;
    }
  }
  
  public boolean duckOffScreen1( ) {
//    return duckRect1.right < 0 || duckRect1.bottom < 0
//        || duckRect1.top > huntingRect.bottom
//        || duckRect1.left > huntingRect.right;
    return duckRect1.right < 0;
  }
  public boolean duckOffScreen2( ) {
//    return duckRect2.right < 0 || duckRect2.bottom < 0
//            || duckRect2.top > huntingRect.bottom
//            || duckRect2.left > huntingRect.right;
    return duckRect2.right < 0;
  }

  public boolean duckDown1( ) {
    return duckRect1.top > huntingRect.bottom;
  }
  public boolean duckDown2( ) {
    return duckRect2.top > huntingRect.bottom;
  }


  public void moveBullet( ) {
    bulletCenter.x += bulletSpeed * Math.cos( bulletAngle ) * deltaTime;
    bulletCenter.y -= bulletSpeed * Math.sin( bulletAngle ) * deltaTime;
  }
  
  public boolean bulletOffScreen( ) {
    return bulletCenter.x - bulletRadius > huntingRect.right 
        || bulletCenter.y + bulletRadius < 0;
  }
  
  public void loadBullet( ) {
    bulletFired = false;
    bulletCenter.x = ( int ) ( cannonCenter.x 
      + cannonRadius * Math.cos( cannonAngle ) );
    bulletCenter.y = ( int ) ( cannonCenter.y 
      - cannonRadius * Math.sin( cannonAngle ) );
  }

  public boolean duckHit1() {
    return duckRect1.intersects(
      bulletCenter.x - bulletRadius, bulletCenter.y - bulletRadius, 
      bulletCenter.x + bulletRadius, bulletCenter.y + bulletRadius );
  }
  public boolean duckHit2() {
    return duckRect2.intersects(
            bulletCenter.x - bulletRadius, bulletCenter.y - bulletRadius,
            bulletCenter.x + bulletRadius, bulletCenter.y + bulletRadius );
  }
}