package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import screens.MainGame;

public class MyGestures implements GestureDetector.GestureListener, InputProcessor {

    public static String message = "No gesture performed yet";
    //Gestures
    public static boolean pan = false;
    public static boolean tap = false;
    public static float tapX,tapY;
    public static float panX,panY;
    public static float dragX,dragY;
    public static boolean zoom = false, zoomOut = false, zoomMoving = false;
    public static float zoomInit, zoomFin;
    public static float longPX, longPY;
    public static boolean longPress;
    public static boolean touchDown = false;
    public static boolean touchDragged = false;
    public static Vector3 touchPos;

    public static boolean touchUp;
    public static Vector3 firstTouch, newDelta,newTouch,delta,diff;
    public static Vector3 firstTouchTest, newDeltaTest, deltaTest, newTouchTest;

    //second input
    public static boolean touchUp2;
    public static Vector3 firstTouch2, newDelta2,newTouch2,delta2,diff2;
    public static Vector3 firstTouchTest2, newDeltaTest2, deltaTest2, newTouchTest2;
    public static boolean touchDragged2 = false;
    public static boolean touchDown2 = false;


    public GestureDetector gd;
    public MyGestures(){
        gd = new GestureDetector(this);
        touchPos = new Vector3();
        firstTouch = new Vector3();
        newTouch = new Vector3();
        delta = new Vector3();
        newDelta = new Vector3();
        diff = new Vector3();
        //second input
        firstTouch2 = new Vector3();
        newTouch2 = new Vector3();
        delta2 = new Vector3();
        newDelta2 = new Vector3();
        diff2 = new Vector3();
        gd.setLongPressSeconds(0.8f);

        firstTouchTest = new Vector3();
        newDeltaTest = new Vector3();
        deltaTest = new Vector3();
        newTouchTest = new Vector3();

        firstTouchTest2 = new Vector3();
        newDeltaTest2 = new Vector3();
        deltaTest2 = new Vector3();
        newTouchTest2 = new Vector3();

    }

    public static void update(){
        tap = false;
        pan = false;
        zoom = false;
        longPress = false;
        touchDown = false;
        touchDragged = false;
        touchUp = false;
        //
        touchUp2 = false;
        touchDragged2 = false;
        touchDown2 = false;
    }

    //TAP
    @Override
    public boolean tap(float x, float y, int count, int button) {
        message = "Tap performed, finger" + Integer.toString(button);
        touchPos.set(x,y,0);
        tap = true;
        tapX = x;
        tapY = y;
        return true;
    }
    public static boolean isTap(){
        return tap;
    }
    //

    // PAN
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        pan = true;
        panX = deltaX;
        panY = deltaY;
        touchPos.x = x;
        touchPos.y = y;
        return true;
    }
    public static boolean isPan(){
        return pan;
    }

    // ZOOM

    @Override
    public boolean zoom(float initialDistance, float distance) {
        message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
                " Distance: " + Float.toString(distance);
        zoom = true;
        zoomInit = initialDistance;

        if(Math.abs(distance - zoomFin) <= 5){
            zoomMoving = false;
        }
        zoomFin = distance;
        zoomMoving = true;
        if(distance - initialDistance < 0) zoomOut = true;
        else zoomOut = false;
        return true;
    }
    public static boolean isZoom(){
        return zoom && zoomMoving;
    }

    //LONGPRESS

    @Override
    public boolean longPress(float x, float y) {
        message = "Long press performed";
        longPress = true;
        longPX = x;
        longPY = y;
        return true;
    }

    public static boolean isLongPress(){
        return longPress;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(pointer == 0){
            touchDown = true;
        }
        if(pointer == 1){
            touchDown2 = true;
        }

        return true;
    }
    public static boolean isTouchDown(){
        return touchDown;
    }

    public static boolean isTouchDown2(){
        return touchDown2;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        message = "Fling performed, velocity:" + Float.toString(velocityX) +
                "," + Float.toString(velocityY);
        return true;
    }




    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        message = "Pinch performed";
        return true;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        message = "panStop";
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }




    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer == 0){
            Gdx.app.log("TOUCH DOWN","POINTER 1");
            firstTouch.set(screenX, screenY, 0);
            firstTouchTest.set(screenX, screenY, 0);
            if(MainGame.cam != null) MainGame.cam.unproject(firstTouchTest);
            delta.set(0, 0, 0);
            deltaTest.set(0,0,0);
        }
        else if(pointer == 1){
            Gdx.app.log("TOUCH DOWN","POINTER 2");
            firstTouch2.set(screenX, screenY, 0);
            firstTouchTest2.set(screenX,screenY,0);
            if(MainGame.cam != null) MainGame.cam.unproject(firstTouchTest2);
            delta2.set(0, 0, 0);
            deltaTest2.set(0,0,0);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer == 0){
            touchUp = true;
        }
        else if(pointer == 1){
            touchUp2 = true;
        }
        return false;
    }

    public static boolean isTouchUp(){
        return touchUp;
    }
    public static boolean isTouchUp2(){
        return touchUp2;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if(pointer == 0){
            touchDragged = true;
            newTouch.set(screenX, screenY, 0);

            newDelta = newTouch.cpy().sub(firstTouch);

            diff = delta.cpy().sub(newDelta);

            newTouchTest.set(screenX, screenY, 0);
            MainGame.cam.unproject(newTouchTest);
            newDeltaTest = newTouchTest.cpy().sub(firstTouchTest);
            diff = deltaTest.cpy().sub(newDeltaTest);
            deltaTest = newDeltaTest;
            delta = newDelta;
        }
        else if(pointer == 1){
            touchDragged2 = true;
            newTouch2.set(screenX, screenY, 0);

            newDelta2 = newTouch2.cpy().sub(firstTouch2);

            diff2 = delta2.cpy().sub(newDelta2);

            newTouchTest2.set(screenX, screenY, 0);
            MainGame.cam.unproject(newTouchTest2);
            newDeltaTest2 = newTouchTest2.cpy().sub(firstTouchTest2);
            diff2 = deltaTest2.cpy().sub(newDeltaTest2);
            deltaTest2 = newDeltaTest2;
            delta2 = newDelta2;



        }



        return false;
    }
    public static boolean isTouchDragged(){
        return touchDragged;
    }
    public static boolean isTouchDragged2(){
        return touchDragged2;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public static void resetDiff(int pointer){
        if(pointer == 0){
            diff.set(0, 0, 0);
        }
        else if(pointer == 1){
            diff2.set(0, 0, 0);
        }

    }
}
