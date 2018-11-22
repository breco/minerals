package screens;

import com.artificialmemories.minerals.Initial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {
    OrthographicCamera cam;
    Initial game;
    Viewport viewport;

    //HUD
    Stage stage;
    Skin skin;


    //TEST

    int virtualWidth = 1280;
    int virtualHeight = 720;
    Sprite color;
    public MainMenu(final Initial game){
        this.game = game;
        cam = new OrthographicCamera();
        cam.position.set(cam.viewportHeight/2,cam.viewportWidth/2, 0);
        viewport = new FillViewport(virtualHeight, virtualWidth,cam);
        //viewport = new ScreenViewport(cam);
        viewport.apply();
        color = new Sprite(new Texture(Gdx.files.internal("red.png")));
        color.setSize(virtualHeight,virtualWidth);
        color.setPosition(0,0);


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createBasicSkin();
        TextButton newGameButton = new TextButton("Start game", skin);
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/20);
        newGameButton.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent event,float x, float y){
               game.setScreen(new WorldScreen(game));
           }
        });
        stage.addActor(newGameButton);

        newGameButton = new TextButton("Options", skin);
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/3 - Gdx.graphics.getHeight()/20);
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                Gdx.app.log("CLICKED2","YES2");
            }
        });
        stage.addActor(newGameButton);

    }

    public void createBasicSkin(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;

        BitmapFont font = generator.generateFont(parameter);
        skin = new Skin();
        skin.add("default",font);
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4,Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default",textButtonStyle);

    }

    public void input(){
    }

    public void update(){
        input();
    }

    public void draw(){
        color.draw(game.batch);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("FPS",""+Gdx.graphics.getFramesPerSecond());

        // 1)Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 2)Input handling
        input();

        // 3)Update system
        // 3.1)---> Update Cam

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        // 3.2)---> Update Game
        update();


        // 4)Draw
        game.batch.begin();
        draw();
        stage.act();
        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }
}

