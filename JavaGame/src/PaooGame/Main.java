package PaooGame;

/*!
    \mainpage Descriere
    Adventure Time Game este un joc RPG single-player în care jucătorul trebuie să învingă toti inamicii și să isi  imbunatateasca abilitatile de atac, aparare si viteza de atac pentru a reusi sa ii invinga pe inamicii mai puternici. Jocul se petrece pe doua harti din perspectiva ortogonala. Prima harta este cea principal unde se afla toti inamicii, iar a doua cea de batalie intre persoanje. Scopul jocului este acela de a invinge inamicii, incepand cu cei mai slabi si urmand cu cei mai puternici. Fiecare personaj invins aduce cu sine posibilitatea de a alege creste fie atacul cu 10 puncte, fie apararea cu 5, fie viteza de atac cu 5 a personajului principal.

*/

public class Main
{
    public static void main(String[] args)
    {
        Game.getInstance().StartGame();     //Singleton
    }
}
