package com.ozma.samew.woo1.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ozma.samew.woo1.Core;

public class Menu {
    
    private Table table;
    private Stage stage;
    private Button btn;
    
    public Menu() {
        stage = new Stage(new ScreenViewport(Core.camera), Core.batch);
        table = new Table();
        table.setDebug(true);
        table.setFillParent(true);
        stage.addActor(table);
        addStartBtn();
        
    }
    
    /**
     * Adds the start btn with click listener
     */
    private void addStartBtn() {
        ButtonStyle btnStyle = new ButtonStyle();
        btn = new Button();
        btn.setStyle(btnStyle);
        btn.setWidth(200f);
        btn.setHeight(100f);
        
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // start over world stage TODO
                Core.mapManager.loadMap("assets/map/test.tmx");
            }
        });
        table.row();
        table.add(btn);
    }

    public Stage getStage() {
        return stage;
    }
    
    
}
