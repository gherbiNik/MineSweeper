package ch.supsi.frontend;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.application.cell.MineRevealerApplication;
import ch.supsi.backend.application.game.GameApplication;
import ch.supsi.backend.application.game.GameApplicationInterface;
import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.business.game.GameBoardBusiness;
import ch.supsi.backend.business.game.GameBombBusiness;
import ch.supsi.backend.application.gameMapper.GameStateMapperApplication;
import ch.supsi.backend.application.l10n.TranslationsApplication;
import ch.supsi.backend.application.preferences.PreferencesApplication;
import ch.supsi.backend.business.l10n.TranslationsBusiness;
import ch.supsi.backend.business.mapper.GameStateMapper;
import ch.supsi.backend.business.mapper.GameStateMapperBusiness;
import ch.supsi.backend.business.mine.BombPlacer;
import ch.supsi.backend.business.mine.CellAction;
import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.mine.MineRevealer;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.backend.business.model.GameLogic;
import ch.supsi.backend.business.preferences.PreferencesBusiness;
import ch.supsi.backend.business.service.GameSaveServiceBusiness;
import ch.supsi.backend.business.service.IGameSaveServiceBusiness;
import ch.supsi.backend.dataAccess.l10n.TranslationsPropertiesDataAccess;
import ch.supsi.backend.dataAccess.preferences.PreferencesDataAccess;
import ch.supsi.backend.dataAccess.states.GameSaveServiceFactory;
import ch.supsi.backend.dataAccess.states.JacksonGameSaveService;
import ch.supsi.frontend.controller.GameController;
import ch.supsi.frontend.controller.GameEventHandler;
import ch.supsi.frontend.controller.PlayerEventHandler;
import ch.supsi.frontend.controller.gameMapperController.GameMapperController;
import ch.supsi.frontend.controller.gameMapperController.IInfoController;
import ch.supsi.frontend.controller.gameMapperController.InfoController;
import ch.supsi.frontend.controller.preferences.PreferencesController;
import ch.supsi.frontend.model.game.GameBoardModel;
import ch.supsi.frontend.model.game.GameBoardModelInterface;
import ch.supsi.frontend.model.game.GameModel;
import ch.supsi.frontend.model.gameMapperModel.GameMapperModel;
import ch.supsi.frontend.model.gameMapperModel.IGameMapperModel;
import ch.supsi.frontend.model.preferences.PreferencesModel;
import ch.supsi.frontend.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class MainFx extends Application {

    public static final String APP_TITLE = "mine sweeper";

    private final GameModel gameModel;
    private final IGameMapperModel gameMapperModel;
    private final ControlledFxView menuBarView;
    private final ControlledFxView gameBoardView;
    private final PreferenceView preferenceView;
    private final UncontrolledFxView userFeedbackView;
    private final GameEventHandler gameEventHandler;
    private final PlayerEventHandler playerEventHandler;
    private final ExitView exitView;
    private BorderPane mainBorderPane;

    public MainFx() {

        // GAME MODEL
        GameBoardBusiness gameBoardBusiness = new GameBoardBusiness();

        MinePlacementStrategy bombPlacer = new BombPlacer(gameBoardBusiness);

        GameBombBusiness gameBombBusiness = new GameBombBusiness();
        JacksonGameSaveService jacksonGameSaveService = GameSaveServiceFactory.createJacksonSaveService();
        GameSaveServiceBusiness gameSaveServiceBusiness = new GameSaveServiceBusiness(jacksonGameSaveService);


        PreferencesDataAccess preferencesDataAccess = PreferencesDataAccess.getInstance();
        PreferencesBusiness preferencesBusiness = PreferencesBusiness.getInstance(preferencesDataAccess);
        PreferencesApplication preferencesApplication = PreferencesApplication.getInstance(preferencesBusiness);
        PreferencesModel preferencesModel = PreferencesModel.getInstance(preferencesApplication);
        PreferencesController preferencesController=PreferencesController.getInstance(preferencesModel);

        TranslationsPropertiesDataAccess translationsPropertiesDataAccess = TranslationsPropertiesDataAccess.getInstance();
        TranslationsBusiness translationsBusiness = TranslationsBusiness.getInstance(translationsPropertiesDataAccess);
        TranslationsApplication translationsApplication = TranslationsApplication.getInstance(preferencesBusiness, translationsBusiness);


        gameBoardBusiness.setDimensions(9);
        gameBombBusiness.setMinBomb(1);
        gameBombBusiness.setMaxBomb(80);




        GameLogic gameLogic = GameLogic.getInstance(bombPlacer, gameBoardBusiness, gameBombBusiness, preferencesBusiness);
        MineRevealer mineRevealer = MineRevealer.getInstance(gameBoardBusiness, gameLogic);
        gameLogic.setMineRevealer(mineRevealer);
        MineRevealerApplication mineRevealerApplication = MineRevealerApplication.getInstance(mineRevealer);
        GameApplication gameApplicationInterface = GameApplication.getInstance(gameLogic);

        GameBoardApplication gameBoardApplication = GameBoardApplication.getInstance(gameBoardBusiness);
        GameBoardModel gameBoardModel = GameBoardModel.getInstance(gameBoardApplication);
        this.gameModel = GameModel.getInstance(gameApplicationInterface);
        GameStateMapper gameStateMapper = GameStateMapper.getInstance( gameSaveServiceBusiness, gameLogic);
        GameStateMapperApplication gameStateMapperApplication = GameStateMapperApplication.getInstance(gameApplicationInterface, gameStateMapper);
        this.gameMapperModel = GameMapperModel.getInstance(gameStateMapperApplication);



        // VIEWS
        this.menuBarView = MenuBarViewFxml.getInstance();
        this.gameBoardView = GameBoardViewFxml.getInstance();
        this.userFeedbackView = UserFeedbackViewFxml.getInstance();
        this.preferenceView = PreferenceView.getInstance();
        this.exitView = ExitView.getInstance();

        // CONTROLLERS
        //TODO casting corretto? - Opzione: dichiarare gameModel col tipo specifico (e non AbstractModel)
        GameController controller = GameController.getInstance((GameModel) gameModel);
        GameMapperController gameMapperController = GameMapperController.getInstance(gameMapperModel);
        this.gameEventHandler = controller;
        this.playerEventHandler = controller;


        // SCAFFOLDING of M-V-C
        this.exitView.initialize(); // todo future adds
        this.preferenceView.initialize(preferencesController, translationsApplication);
        this.menuBarView.initialize(this.gameEventHandler, this.gameModel, gameMapperController, this.preferenceView,translationsApplication, exitView );
        this.gameBoardView.initialize(this.playerEventHandler, this.gameModel, gameMapperController, gameBoardModel);
        this.userFeedbackView.initialize(this.gameModel, translationsApplication);

        // INFO
        IInfoController infoController = InfoController.getInstance((InfoView) userFeedbackView);
        ((InfoViewInit) this.menuBarView).initialize(this.gameEventHandler, this.gameModel, gameMapperController, infoController, preferenceView,translationsApplication, exitView);

        this.userFeedbackView.initialize(this.gameModel, translationsApplication);
        //this.welcomeView.initialize(this.gameModel);
        //GameController.getInstance().setMainFx(this);
        controller.initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView), preferencesBusiness, translationsBusiness);

        gameMapperController.initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView));

    }

//    public  void switchGameBoard() {
//        mainBorderPane.setCenter(this.gameBoardView.getNode());
//    }

    @Override
    public void start(Stage primaryStage) {

//        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeView.fxml"));
//        primaryStage.setTitle("Welcome Page");
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();

        // handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // quit the app
                    // replace this hard close
                    // by delegating the work to a suitable controller
                    primaryStage.close();
                }
        );

        // SCAFFOLDING OF MAIN PANE
        mainBorderPane = new BorderPane();
        mainBorderPane.setTop(this.menuBarView.getNode());
        mainBorderPane.setCenter(this.gameBoardView.getNode());
        mainBorderPane.setBottom(this.userFeedbackView.getNode());

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // PRIMARY STAGE
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
