package PaooGame.Database;
import PaooGame.Audio.AudioAssets;
import PaooGame.Components.Characters.Characters;
import PaooGame.Components.ObjectId;
import PaooGame.ObjectHandlerObserver.ObjectsHandler;

import java.sql.*;

/*! \class Database
    \brief Implementeaza un obiectp prin care se poate accesa baza de date a jocului.
 */
public class Database
{
    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Database.

        Contructorul creeaza o tabela de date pentru setarile jocului in cazul in care nu exista deja una.
     */
    public Database() {
        Connection c;
        Statement stmt;
        PreparedStatement stmt2;
        PreparedStatement stmt3;

        String insert = "INSERT INTO GameSettings (Setting,Value) " + "VALUES (?,?);";
        String newTable = "CREATE TABLE IF NOT EXISTS GameSettings (\n"
                + "	Id integer PRIMARY KEY,\n"
                + "	Setting text,\n"
                + "	Value integer\n"
                + ");";

        String getData = "SELECT Setting,Value from GameSettings";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");
            stmt = c.createStatement();
            stmt2 = c.prepareStatement(insert);
            stmt3 = c.prepareStatement(getData);

            ResultSet rs = stmt3.executeQuery();

            stmt.execute(newTable);

            int volume = -1;

            while (rs.next())
            {
                volume = rs.getInt("Value");
            }

            if(volume == -1) {
                stmt2.setString(1, "Music Volume");
                stmt2.setInt(2, 100);
                stmt2.executeUpdate();

                stmt2.setString(1, "Sounds Volume");
                stmt2.setInt(2, 100);
                stmt2.executeUpdate();
            }

            stmt.close();
            stmt2.close();
            stmt3.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public void NewGame()
        \brief Metoda pentru creerea unei tabele vide, CharactersName.
     */
    public void NewGame() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");
            stmt = c.createStatement();

            String drop = "DROP TABLE IF EXISTS CharactersName;";

            String newTable = "CREATE TABLE IF NOT EXISTS CharactersName (\n"
                    + "	Id integer PRIMARY KEY,\n"
                    + "	CharacterName text,\n"
                    + "	Alive integer,\n"
                    + "	AttackDamage integer,\n"
                    + "	Defence integer,\n"
                    + "	AttackSpeed integer\n"
                    + ");";

            stmt.executeUpdate(drop);
            stmt.execute(newTable);

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*! \fn public void SaveGame()
        \brief Metoda pentru scrierea datelor specifice caracterelor in tabela CharactersName
     */
    public void SaveGame(boolean[] AliveCharacters, ObjectsHandler handler)
    {
        Connection c = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        String delete = "DELETE FROM CharactersName WHERE id = ?";
        String insert = "INSERT INTO CharactersName (CharacterName,Alive,AttackDamage,Defence,AttackSpeed) " + "VALUES (?,?,?,?,?);";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");
            stmt = c.prepareStatement(insert);
            stmt2 = c.prepareStatement(delete);


            for(int i = 1; i <= AliveCharacters.length; ++i)
            {
                stmt2.setInt(1, i);
                stmt2.executeUpdate();
            }


            for(int i = 0; i < AliveCharacters.length; ++i) {

                if(AliveCharacters[i]) {
                    Characters character = handler.getCharacterTypeReference(ObjectId.values()[i]);

                    stmt.setString(1, ObjectId.values()[i].name());
                    stmt.setInt(2, 1);

                    if(character != null) {
                        stmt.setInt(3, character.getAttackDamage());
                        stmt.setInt(4, character.getDefence());
                        stmt.setInt(5, character.getAttackSpeed());
                    }
                    else
                    {
                        stmt.setInt(3, -1);
                        stmt.setInt(4, -1);
                        stmt.setInt(5, -1);
                    }
                }
                else
                {
                    stmt.setString(1, ObjectId.values()[i].name());
                    stmt.setInt(2, 0);

                    stmt.setInt(3, -1);
                    stmt.setInt(4, -1);
                    stmt.setInt(5, -1);
                }

                stmt.executeUpdate();
            }

            stmt2.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*! \fn public void ContinueGame()
        \brief Metoda pentru interogarea si citirea din tabla CharactersName.
     */
    public void ContinueGame(boolean[] AliveCharacters, ObjectsHandler handler) {
        Connection c = null;
        PreparedStatement stmt = null;

        String getData = "SELECT CharacterName,Alive,AttackDamage,Defence,AttackSpeed from CharactersName";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");

            stmt = c.prepareStatement(getData);
            ResultSet rs = stmt.executeQuery();

            String CharacterName;
            int Alive;
            int AD;
            int Def;
            int AS;
            int ind = 0;

            while (rs.next()) {
                CharacterName = rs.getString("CharacterName");
                Alive = rs.getInt("Alive");

                AD = rs.getInt("AttackDamage");
                Def = rs.getInt("Defence");
                AS = rs.getInt("AttackSpeed");

                if (CharacterName.equals(ObjectId.Player.name())) {
                    handler.getMainCharacter().setAttackDamage(AD);
                    handler.getMainCharacter().setDefence(Def);
                    handler.getMainCharacter().setAttackSpeed(AS);
                }

                AliveCharacters[ind] = Alive == 1;
                ++ind;

                if (Alive == 0) {
                    for (int i = 0; i < handler.getCharactersHandler().size(); ++i) {
                        if (!AliveCharacters[handler.getCharactersHandler().get(i).GetId().ordinal()]) {
                            handler.removeCharacter(handler.getCharactersHandler().get(i));
                            --i;
                        }
                    }
                }

            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*! \fn public void ContinueGame()
        \brief Metoda pentru interogarea tabelei GameSettings si initializarea volumului sunetelor si al muzicii din joc.
     */
    public static void InitVolumeValues()
    {
        Connection c;
        PreparedStatement stmt = null;


        String getData = "SELECT Setting,Value from GameSettings";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");

            stmt = c.prepareStatement(getData);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                String setting = rs.getString("Setting");
                int volume = rs.getInt("Value");

                if(setting.equals("Music Volume"))
                {
                    AudioAssets.MUSICVOLUME = volume;
                }
                else if(setting.equals("Sounds Volume")){
                    AudioAssets.SOUNDSVOLUME = volume;
                }
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /*! \fn public void ContinueGame()
        \brief Metoda pentru interogarea tabelei GameSettings si setarea volumului sunetelor si al muzicii din joc.
     */
    public void setVolumeValues(float musicVolume, float soundsVolume)
    {
        Connection c = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        String delete = "DELETE FROM GameSettings WHERE id = ?";
        String insert = "INSERT INTO GameSettings (Setting,Value) " + "VALUES (?,?);";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");
            stmt = c.prepareStatement(insert);
            stmt2 = c.prepareStatement(delete);


            for(int i = 1; i <= 2; ++i)
            {
                stmt2.setInt(1, i);
                stmt2.executeUpdate();
            }


            stmt.setString(1, "Music Volume");
            stmt.setInt(2, (int) musicVolume);
            stmt.executeUpdate();

            stmt.setString(1, "Sounds Volume");
            stmt.setInt(2, (int) soundsVolume);
            stmt.executeUpdate();

            stmt2.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Database.InitVolumeValues();
        AudioAssets.InitVolume();

    }

    /*! \fn public public boolean isCharactersNameTableEmpty()
        \brief Returneaza true daca tabela CharactersName este vida.
     */
    public boolean isCharactersNameTableEmpty()
    {
        Connection c = null;
        PreparedStatement stmt = null;

        String getData = "SELECT CharacterName,Alive,AttackDamage,Defence,AttackSpeed from CharactersName";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Database.db");

            stmt = c.prepareStatement(getData);
            ResultSet rs = stmt.executeQuery();

            int AD = 0;
            int Def = 0;
            int AS = 0;

            while (rs.next()) {
                AD = rs.getInt("AttackDamage");
                Def = rs.getInt("Defence");
                AS = rs.getInt("AttackSpeed");
            }

            stmt.close();
            c.close();

            return AD == 0 && Def == 0 && AS == 0;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
