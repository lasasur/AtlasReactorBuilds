package app.com.example.android.atlasreactorbuilds;

import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fábio on 23/05/2017.
 */

public class XmlPullParserHandler{

    private ArrayList<Freelancer> freelancers;
    private Freelancer freelancer;
    private Ability ability;
    private Mod mod;
    private String text;

    private static final String TAG = "LOG DE MENSAGEM DEBUG";

    public XmlPullParserHandler(){
        //Construtor da classe
        freelancers = new ArrayList<Freelancer>();
    }

    public ArrayList<Freelancer> getFreelancers(){
        return freelancers;
    }

    public ArrayList<Freelancer> parse(InputStream is){
        //obtem a lista de freelancers a partir de assets/freelancers.xml
        XmlPullParserFactory factory    = null;
        XmlPullParser parser            = null;

        List<Ability> abilities = new ArrayList<Ability>();
        List<Mod> mods          = new ArrayList<Mod>();

        try{
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            parser.setInput(is , null);
            int eventType = parser.getEventType();
            String tagName = null;

            while(eventType != XmlPullParser.END_DOCUMENT){

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG , "Inicio do documento");
                        Log.i(TAG , "--------------");
                        break;
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        Log.i(TAG , "Começo da tag: "+tagName);
                        Log.i(TAG , "--------------");
                        if(tagName.equalsIgnoreCase("freelancer")){
                            abilities.clear();
                            freelancer = new Freelancer();
                            freelancer.setName(parser.getAttributeValue(null , "name"));
                            freelancer.setRole(parser.getAttributeValue(null , "role"));
                            freelancer.setHealth(Integer.parseInt(parser.getAttributeValue(null , "health")));
                            freelancer.setAffiliation(parser.getAttributeValue(null , "affiliation"));
                        }
                        else if(tagName.equalsIgnoreCase("ability")){
                            ability = new Ability();
                            mods.clear();
                            ability.setName(parser.getAttributeValue(null , "name"));
                            ability.setCooldown(Integer.parseInt(parser.getAttributeValue(null , "cooldown")));
                            ability.setDamage(Integer.parseInt(parser.getAttributeValue(null , "damage")));

                            if(parser.getAttributeValue(null , "phase").equalsIgnoreCase("Prep")){
                                ability.setPhase(Ability.PREP_PHASE);
                            }
                            else if(parser.getAttributeValue(null , "phase").equalsIgnoreCase("Dash")){
                                ability.setPhase(Ability.DASH_PHASE);
                            }
                            else{
                                ability.setPhase(Ability.BLAST_PHASE);
                            }
                            ability.setFreeAction((parser.getAttributeValue(null , "freeaction") == "true"));
                        }
                        else if(tagName.equalsIgnoreCase("mod")){
                            mod = new Mod();
                            mod.setName(parser.getAttributeValue(null , "name"));
                            mod.setValue(Integer.parseInt(parser.getAttributeValue(null , "value")));
                            Log.i(TAG , "mod: "+mod.getName());
                            Log.i(TAG , "mod: "+mod.getValue());
                            Log.i(TAG , "---------------------");
                        }
                        break;
                    case XmlPullParser.TEXT:
                        Log.i(TAG , "texto da tag: "+tagName);
                        Log.i(TAG , "--------------");
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        Log.i(TAG , "Fim da tag: "+tagName);
                        Log.i(TAG , "--------------");
                        if(tagName.equalsIgnoreCase("freelancer")){
                            freelancer.setAbility1(abilities.get(0));
                            freelancer.setAbility2(abilities.get(1));
                            freelancer.setAbility3(abilities.get(2));
                            freelancer.setAbility4(abilities.get(3));
                            freelancer.setAbility5(abilities.get(4));
                            freelancers.add(freelancer);
                        }
                        else if(tagName.equalsIgnoreCase("ability")) {
                            ability.setMods(mods);
                            abilities.add(ability);
                        }
                        else if(tagName.equalsIgnoreCase("mod")){
                            mod.setDescription(text);
                            mods.add(mod);
                        }
                        else if(tagName.equalsIgnoreCase("energy")){
                            ability.setEnergy(text);
                        }
                        else if(tagName.equalsIgnoreCase("description")){
                            ability.setDescription(text);
                        }
                        else if(tagName.equalsIgnoreCase("about")){
                            freelancer.setBio(text);
                        }
                        break;
                    default:
                        Log.i(TAG , "Evento não reconhecido. Ultima tag: "+tagName);
                        Log.i(TAG , "EventType: "+eventType);
                        Log.i(TAG , "--------------");
                        break;
                }
                eventType = parser.next();
            }
        }
        catch(XmlPullParserException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return freelancers;
    }

    public Freelancer getFreelancerByName(InputStream is , String name) {
        /*
        *obtem a lista de freelancers a partir de assets/freelancers.xml
        *
        * Percorre o arquivo xml freelancers até encontrar a tag freelancer com o atributo
        * name = name, quando encontrado cria um objeto Freelancer com os dados da tag freelancer
        * */
        freelancer = null;
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        List<Ability> abilities = new ArrayList<Ability>();
        List<Mod> mods = new ArrayList<Mod>();

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            String tagName = null;
            boolean flFound = false;

            search:
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String xmlFlName = parser.getAttributeValue(null , "name") != null ?
                        parser.getAttributeValue(null , "name") : "";
                if(eventType == XmlPullParser.START_TAG && (xmlFlName.equalsIgnoreCase(name))){
                    flFound = true;
                }

                if (flFound) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            Log.i(TAG, "Inicio do documento");
                            Log.i(TAG, "--------------");
                            break;
                        case XmlPullParser.START_TAG:
                            tagName = parser.getName();
                            Log.i(TAG, "Começo da tag: " + tagName);
                            Log.i(TAG, "--------------");
                            if (tagName.equalsIgnoreCase("freelancer")) {
                                abilities.clear();
                                freelancer = new Freelancer();
                                freelancer.setName(parser.getAttributeValue(null, "name"));
                                freelancer.setRole(parser.getAttributeValue(null, "role"));
                                freelancer.setHealth(Integer.parseInt(parser.getAttributeValue(null, "health")));
                                freelancer.setAffiliation(parser.getAttributeValue(null, "affiliation"));
                            } else if (tagName.equalsIgnoreCase("ability")) {
                                ability = new Ability();
                                mods.clear();
                                ability.setName(parser.getAttributeValue(null, "name"));
                                ability.setCooldown(Integer.parseInt(parser.getAttributeValue(null, "cooldown")));
                                ability.setDamage(Integer.parseInt(parser.getAttributeValue(null, "damage")));

                                if (parser.getAttributeValue(null, "phase").equalsIgnoreCase("Prep")) {
                                    ability.setPhase(Ability.PREP_PHASE);
                                } else if (parser.getAttributeValue(null, "phase").equalsIgnoreCase("Dash")) {
                                    ability.setPhase(Ability.DASH_PHASE);
                                } else {
                                    ability.setPhase(Ability.BLAST_PHASE);
                                }
                                ability.setFreeAction((parser.getAttributeValue(null, "freeaction") == "true"));
                            } else if (tagName.equalsIgnoreCase("mod")) {
                                mod = new Mod();
                                mod.setName(parser.getAttributeValue(null, "name"));
                                mod.setValue(Integer.parseInt(parser.getAttributeValue(null, "value")));
                                Log.i(TAG, "mod: " + mod.getName());
                                Log.i(TAG, "mod: " + mod.getValue());
                                Log.i(TAG, "---------------------");
                            }
                            break;
                        case XmlPullParser.TEXT:
                            Log.i(TAG, "texto da tag: " + tagName);
                            Log.i(TAG, "--------------");
                            text = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            tagName = parser.getName();
                            Log.i(TAG, "Fim da tag: " + tagName);
                            Log.i(TAG, "--------------");
                            if (tagName.equalsIgnoreCase("freelancer")) {
                                freelancer.setAbility1(abilities.get(0));
                                freelancer.setAbility2(abilities.get(1));
                                freelancer.setAbility3(abilities.get(2));
                                freelancer.setAbility4(abilities.get(3));
                                freelancer.setAbility5(abilities.get(4));
                                break search;
                            } else if (tagName.equalsIgnoreCase("ability")) {
                                ability.setMods(mods);
                                abilities.add(ability);
                            } else if (tagName.equalsIgnoreCase("mod")) {
                                mod.setDescription(text);
                                mods.add(mod);
                            } else if (tagName.equalsIgnoreCase("energy")) {
                                ability.setEnergy(text);
                            } else if (tagName.equalsIgnoreCase("description")) {
                                ability.setDescription(text);
                            } else if (tagName.equalsIgnoreCase("about")) {
                                freelancer.setBio(text);
                            }
                            break;
                        default:
                            Log.i(TAG, "Evento não reconhecido. Ultima tag: " + tagName);
                            Log.i(TAG, "EventType: " + eventType);
                            Log.i(TAG, "--------------");
                            break;
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) { e.printStackTrace(); }

        return freelancer;
    }
}
