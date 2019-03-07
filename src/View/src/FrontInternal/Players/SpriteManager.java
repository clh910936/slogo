package FrontInternal.Players;

import java.util.*;

public abstract class SpriteManager {
    /** All the sprite objects currently in play */
    //protected final static Map<Integer, Sprite> GAME_ACTORS = new HashMap<>();

    /** Used when turtles needs to disappear/get deleted
     */
    protected final static Set<Sprite> CLEAN_UP_SPRITES = new HashSet();

    /** */
//    public List<Sprite> getAllSprites() {
//        return (List<Sprite>) GAME_ACTORS.values();
//    }

    public abstract void addSprites(Sprite... sprites);

    /** Returns a set of sprite objects to be removed from the GAME_ACTORS.
     * @return CLEAN_UP_SPRITES
     */
    public Set<Sprite> getSpritesToBeRemoved() {
        return CLEAN_UP_SPRITES;
    }

    /**
     * Adds sprite objects to be removed
     * @param sprites varargs of sprite objects.
     */
    public void addSpritesToBeRemoved(Sprite... sprites) {
        if (sprites.length > 1) {
            CLEAN_UP_SPRITES.addAll(Arrays.asList((Sprite[]) sprites));
        } else {
            CLEAN_UP_SPRITES.add(sprites[0]);
        }
    }

    //protected Sprite get(int turtleId) {
        //return GAME_ACTORS.get(turtleId);
    //}

    /**
     * VarArgs of sprite objects to be removed from the game.
     * @param sprites
     */
//    public void removeSprites(Sprite... sprites) {
//        GAME_ACTORS.removeAll(Arrays.asList(sprites));
//    }



    /**
     * Removes sprite objects and nodes from all
     * temporary collections such as:
     * CLEAN_UP_SPRITES.
     * The sprite to be removed will also be removed from the
     * list of all sprite objects called (GAME_ACTORS).
     */
//    public void cleanupSprites() {
//        // remove from actors list
//        GAME_ACTORS.removeAll(CLEAN_UP_SPRITES);
//
//        // reset the clean up sprites
//        CLEAN_UP_SPRITES.clear();
//    }
}
