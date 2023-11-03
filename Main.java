


class Main{


    public static void main(String ...args){
    
        float salaireDeBase = 67500;
        float tauxPensionVieillesse = (float)4.20;
        float salaireBruteTaxable = 79250;

        Main oMain = new Main();

        System.out.println("**************retenues salariales**********");
        
        //salaire net categoriel mensuel
        float snc = oMain.getSNCMensuel(salaireBruteTaxable, tauxPensionVieillesse);
        System.out.println("salaire net categoriel mensuel: "+  snc);

        //irpp: impôt sur le revenu des personnes physiques
        float irpp = oMain.getimpotSurLeRevenuDesPersonnesPhysiques(snc);
        System.out.println("impôt sur le revenu des personnes physiques: "+  irpp);

        //centime individuel communaux
        float centimeIndividuelCommunaux = oMain.getCentimesIndividuelsCommunaux(irpp);
        System.out.println("centime individuel communaux: "+  centimeIndividuelCommunaux);

        //taxe communale
        float taxeCommunaleMensuelle = oMain.getTaxeCommunaleMensuelle(salaireDeBase);
        System.out.println("taxe communale: "+  taxeCommunaleMensuelle);

        //pension vieillesse
        float pensionViellesseRetenueSalariale = oMain.getPensionViellesse(tauxPensionVieillesse, salaireDeBase);
        System.out.println("pension vieillesse: "+  pensionViellesseRetenueSalariale);


        //Credit Foncier Du Cameroun
        float creditFoncierDuCameroun = oMain.getCreditFoncierDuCameroun(salaireBruteTaxable);
        System.out.println("Credit Foncier Du Cameroun: "+  creditFoncierDuCameroun);

        //Redevence Audio Visuelle
        float redevenceAudioVisuelle = oMain.geRedevenceAudioVisuelle(salaireBruteTaxable);
        System.out.println("Redevence Audio Visuelle: "+ redevenceAudioVisuelle );

        //total retenues salariales
        float totalRetenuesSalariales = irpp + centimeIndividuelCommunaux + taxeCommunaleMensuelle + 
        pensionViellesseRetenueSalariale + creditFoncierDuCameroun + redevenceAudioVisuelle ;
        System.out.println("total retenues salariales: "+ totalRetenuesSalariales );

        //Total With Total Retenue Salariale 
        System.out.println("total retenues salariales: "+ oMain.getTotalWithTotalRetenueSalariale(salaireBruteTaxable, totalRetenuesSalariales));



        System.out.println("\n**************Charges patronales**********");

        //allocation familliale
        float allocationFamilliale = oMain.getAllocationFamilliale(salaireDeBase);
        System.out.println("allocation familliale: "+  allocationFamilliale);

        //pension Viellesse Charge Patronale
        float pensionViellesseChargePatronale = oMain.getPensionViellesseChargePatronale(salaireDeBase);
        System.out.println("pension Viellesse Charge Patronale: "+  pensionViellesseChargePatronale);

        //accident de travail
        float accidentDeTravail = oMain.getAccidentDeTravail(salaireDeBase);
        System.out.println("accident de travail: "+  accidentDeTravail);

        //credit foncier
        float creditFoncierChargePatronale = oMain.getCreditFoncierChargePatronale(salaireDeBase);
        System.out.println("credit foncier: "+  creditFoncierChargePatronale);

        //fne charge patronale
        float fne = oMain.getFne(salaireDeBase);
        System.out.println("fne charge patronale: "+  fne);

        //total charges patronales
        float totalChargesPatronales = allocationFamilliale + pensionViellesseChargePatronale +
        accidentDeTravail +  creditFoncierChargePatronale + fne;
        System.out.println("total retenues salariales: "+ totalChargesPatronales );

        //get Total With Total charges patronales: salaire brute patronal
        System.out.println("get Total With Total charges patronales: salaire brute patronal: "+ 
        oMain.getTotalWithTotalChargesPatronales(salaireBruteTaxable, totalChargesPatronales));

    }


    /**
     * return pension vieillesse
     * @param tauxPensionVieillesse
     * @param salaireDeBase
     * @return
     */
    public float getPensionViellesse(float tauxPensionVieillesse, float salaireDeBase){
        float pensionVieillesse = 0 ;

        pensionVieillesse = salaireDeBase*tauxPensionVieillesse/100;

        return pensionVieillesse;
    }

    /**
     * return salaire net categoriel mensuel
     * @param salaireBruteTaxable
     * @param pensionViellesse
     * @return
     */
    public float getSNCMensuel(float salaireBruteTaxable, float pensionViellesse){
        float snc = 0;

        snc = 70*salaireBruteTaxable/100 - pensionViellesse - 500000/12;

        return snc;
    }

    /**
     * get impôt sur le revenu des personnes physiques
     * @param snc salaire net categoriel mensuel
     * @return
     */
    public float getimpotSurLeRevenuDesPersonnesPhysiques(float snc){
        float irpp = 0;

        if(snc<166667){
            irpp = snc*(float)0.10;
        }
        else if(snc<250000){
            irpp = 16667 + (float)0.15*(snc-166667);
        }
        else if(snc<416667){
            irpp = 29167 + (float)0.25*(snc-250000);
        }
        else if(snc>416667){
            irpp = (float)70833.75 + (float)0.35*(snc-416667);
        }


        return irpp;
    }


    /**
     * get cac centime individuel communaux
     * @param irpp
     * @return
     */
    public float getCentimesIndividuelsCommunaux(float irpp){
        return irpp*10/100;
    }

    /**
     * get cfc Credit Foncier Du Cameroun
     * @param salaireBrutTaxable
     * @return
     */
    public float getCreditFoncierDuCameroun(float salaireBrutTaxable){
        float cfc = 0;

        cfc = salaireBrutTaxable*1/100;
        return cfc;
    }

    /**
     * get rav (Redevence Audio Visuelle)
     * @param salaireBrutTaxable
     * @return
     */
    public float geRedevenceAudioVisuelle(float salaireBrutTaxable){
        float rav = 0;

        if(50000>salaireBrutTaxable){
            rav = 0;
        }
        else if(100000>salaireBrutTaxable){
            rav = 750;
        }
        else if(200000>salaireBrutTaxable){
            rav = 1950;
        }
        else if(300000>salaireBrutTaxable){
            rav = 3250;
        }
        else if(400000>salaireBrutTaxable){
            rav = 4550;
        }
        else if(500000>salaireBrutTaxable){
            rav = 5850;
        }

        return rav;

    }


    /**
     * get taxe de developpement local
     * @param salaireDeBase
     * @return
     */
    public float getTaxeCommunaleMensuelle(float salaireDeBase){
        float tldAnnuel = 0;
        float tldMensuel = 0;

        if(62000<salaireDeBase&&75000>=salaireDeBase){
            tldAnnuel = 3000;
        }
        else if(100000>=salaireDeBase){
            tldAnnuel = 6000;
        }
        else if(125000>=salaireDeBase){
            tldAnnuel = 9000;
        }
        else if(150000>=salaireDeBase){
            tldAnnuel = 12000;
        }
        else if(200000>=salaireDeBase){
            tldAnnuel = 15000;
        }
        else if(250000>=salaireDeBase){
            tldAnnuel = 18000;
        }

        tldMensuel = tldAnnuel/12;

        return tldMensuel;
    }

    /**
     * get Total With Total Retenue Salariale
     * @param salaireBruteTaxable
     * @param totalRetenuesSalariales
     * @return
     */
    public float getTotalWithTotalRetenueSalariale(float salaireBruteTaxable, float totalRetenuesSalariales){
        return salaireBruteTaxable - totalRetenuesSalariales;
    }

    /****charges patronales **********************/

    /**
     * get allocation familliale
     * @param salaireDeBase
     * @return
     */
    public float getAllocationFamilliale(float salaireDeBase){
        return 7*salaireDeBase/100;
    }

    /**
     * get pension Viellesse Charge Patronale
     * @param salaireDeBase
     * @return
     */
    public float getPensionViellesseChargePatronale(float salaireDeBase){
        return (float)4.2*salaireDeBase/100;
    }

    /**
     * get accident de travail
     * @param salaireDeBase
     * @return
     */
    public float getAccidentDeTravail(float salaireDeBase){
        return (float)1.75*salaireDeBase/100;
    }

    /**
     * get credit foncier
     * @param salaireDeBase
     * @return
     */
    public float getCreditFoncierChargePatronale(float salaireDeBase){
        return (float)1.76*salaireDeBase/100;
    }

    /**
     * get fne charge patronale
     * @param salaireDeBase
     * @return
     */
    public float getFne(float salaireDeBase){
        return (float)1.18*salaireDeBase/100;
    }

    /**
     * get Total With Total charges patronales: salaire brute patronal
     * @param salaireBruteTaxable
     * @param totalRetenuesSalariales
     * @return
     */
    public float getTotalWithTotalChargesPatronales(float salaireBruteTaxable, float totalChargesPatronales){
        return salaireBruteTaxable - totalChargesPatronales;
    }


}