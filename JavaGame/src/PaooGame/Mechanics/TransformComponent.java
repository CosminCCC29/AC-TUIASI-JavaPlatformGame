package PaooGame.Mechanics;

/*! \class TransformComponent
    \brief Este responsabila cu tot ce tine de miscarea caracterelor si prop-urilor.
 */
public class TransformComponent
{
    private Vector2D position;
    private Vector2D widthHeight;
    private Vector2D velocity;
    private Vector2D speed;

    private final float gravity = 0.05f;
    private final float MAX_SPEED = 10.0f;
    private boolean falling = true;
    private boolean jumping = true;
    private boolean jumping2  = true;


    public void setPositionx(float x) { position.x = x; }
    public void setPositiony(float y) { position.y = y; }
    public void setVelocityx(float x) { velocity.x = x; }
    public void setVelocityy(float y) { velocity.y = y; }
    public void setSpeedx(float x) { speed.x = x; }
    public void setSpeedy(float y) { speed.y = y; }

    public float getPositionx() { return position.x; }
    public float getPositiony() { return position.y; }
    public float getSpeedx() { return speed.x; };
    public float getSpeedy() { return speed.y; };
    public float getVelocityx() { return velocity.x; }
    public float getVelocityy() { return velocity.y; }

    public void setWidth(float x) { widthHeight.x = x; }
    public void setHeight(float y) { widthHeight.y = y; }
    public float getWidth() { return widthHeight.x; }
    public float getHeight() { return widthHeight.y; }


    public void setFalling(boolean falling) { this.falling = falling; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }
    public void setJumping2(boolean jumping) { this.jumping2 = jumping; }
    public boolean isFalling() { return falling; }
    public boolean isJumping() { return jumping; }
    public boolean isJumping2() { return jumping2; }

    /*! \fn TransformComponent()
        \brief Constructor implicit de initializare al clasei TransformComponent.
     */
    public TransformComponent()
    {
        position = new Vector2D(0.0f,0.0f);
        velocity = new Vector2D(0.0f, 0.0f);
        speed = new Vector2D(0.0f, 0.0f);
        widthHeight = new Vector2D(0.0f,0.0f);
    }

    /*! \fn TransformComponent(float posx, float posy)
        \brief Constructor cu argumente de initializare al clasei TransformComponent.
     */
    public TransformComponent(float posx, float posy)
    {
        position = new Vector2D(posx,posy);
        velocity = new Vector2D(0.0f,0.5f);
        speed = new Vector2D(0.0f,5.0f);
        widthHeight = new Vector2D(0.0f,0.0f);
    }

    /*! \fn TransformComponent(float posx, float posy, float width, float height)
        \brief Constructor cu argumente de initializare al clasei TransformComponent.
     */
    public TransformComponent(float posx, float posy, float width, float height)
    {
        position = new Vector2D(posx,posy);
        velocity = new Vector2D(0.0f,0.5f);
        speed = new Vector2D(0.0f,0.0f);
        widthHeight = new Vector2D(width,height);
    }

    /*! \fn TransformComponent(float posx, float posy, float velx, float vely, float spx, float spy)
        \brief Constructor cu argumente de initializare al clasei TransformComponent.
     */
    public TransformComponent(float posx, float posy, float velx, float vely, float spx, float spy)
    {
        position = new Vector2D(posx,posy);
        velocity = new Vector2D(velx,vely);
        speed = new Vector2D(spx,spy);
        widthHeight = new Vector2D(0.0f,0.0f);
    }

    /*! \fn public void WalkUpdate()
        \brief Metoda de actualizare al pozitiei tuturor componentelor.
     */
    public void WalkUpdate()
    {
        /// Pozitia este egala cu directia de deplasare (-1, 0, 1) inmultita cu viteza de deplasare.
        position.x += velocity.x * speed.x;
        position.y += velocity.y * speed.y;
    }

    /*! \fn public void GravityUpdate()
        \brief Metoda de actualizare al gravitatiei tututor componentelor.
     */
    public void GravityUpdate()
    {
        if(falling)
        {
            velocity.y += gravity;

            if(velocity.y > MAX_SPEED)
                velocity.y = MAX_SPEED;
        }
    }

}
