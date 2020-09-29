package PaooGame.Camera;

import PaooGame.Components.GameObject;
import PaooGame.Mechanics.TransformComponent;
import PaooGame.RefLinks;

/*! \class Camera
    \brief Implementeaza notiunea de camera a jocului.

    Camera urmareste caracterul dat ca referinta atunci cand acesta se misca.
 */
public class Camera
{
    private RefLinks refLinks;
    private GameObject objectFramed;
    public TransformComponent transformComponent;

    /*! \fn public Camera(GameObject objectFramed, RefLinks refLinks)
        \brief Constructor de initializare al clasei Camera.

        \param objectFramed Obiectul pe care va fi axata camera.
        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.

     */
    public Camera(GameObject objectFramed, RefLinks refLinks)
    {
        this.refLinks = refLinks;
        this.objectFramed = objectFramed;

        transformComponent = new TransformComponent(objectFramed.transformComponent.getPositionx() + objectFramed.transformComponent.getWidth()/2 - refLinks.getWndWidth()/2.0f,0,refLinks.getWndWidth(),refLinks.getWndHeight());
        transformComponent.setSpeedx(objectFramed.transformComponent.getSpeedx());
    }

    /*! \fn public Camera(RefLinks refLinks)
        \brief Constructor de initializare al clasei Camera.

        Contructor pentru cazul in care nu dorim sa avem un obiect pe care sa fie axata camera.

        \param refLink Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.

     */
    public Camera(RefLinks refLinks)
    {
        this.refLinks = refLinks;

        transformComponent = new TransformComponent(0,0, refLinks.getWndWidth(),refLinks.getWndHeight());
    }

    /*! \fn public Update()
        \brief Actualizeaza pozitia camerei.
     */
    public void Update()
    {
        transformComponent.setPositionx(objectFramed.transformComponent.getPositionx() + objectFramed.transformComponent.getWidth()/2 - refLinks.getWndWidth()/2.0f);

        if(transformComponent.getPositionx() < 0)
        {
            transformComponent.setPositionx(0);
        }

        if(transformComponent.getPositionx() + transformComponent.getWidth() > refLinks.getCurrentMap().getMapWidth())
        {
            transformComponent.setPositionx(refLinks.getCurrentMap().getMapWidth() - transformComponent.getWidth());
        }
    }

}
