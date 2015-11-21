#!/usr/bin/env python

import os

#Variables de feature ide con su variable de entorno correspondiente
featureVars = { 
        'Sitios':  'SITIOS',
        'Alquileres':  'ALQUILERES',
        'Individual':  'INDIVIDUAL',
        'Grupal':  'GRUPAL',
        'Metricas':    'METRICAS',
        'HistorialViajes': 'HISTORIALVIAJES',
        'ManejoPerfiles':  'MANEJOPERFILES',
        'RedesSociales':   'REDESSOCIALES',
        'Twitter': 'TWITTER',
        'Facebook':    'FACEBOOK',
        'CompartirRedesSociales':  'COMPARTIRREDESSOCIALES',
        'GestionAmigos':   'GESTIONAMIGOS',
        'Notificaciones':  'NOTIFICACIONES',
        'Reportes':    'REPORTES',
        'ReporteMetricas': 'REPORTEMETRICAS',
        'ReporteRutas':    'REPORTERUTAS',
        'ReporteHistorialViajes':  'REPORTEHISTORIALVIAJES',
        'ConfiguradorBicicletas':  'CONFIGURADORBICICLETAS',
        'PromocionesContexto': 'PROMOCIONESCONTEXTO'
    }


enabledfeatures = {key: False for key in featureVars.keys()}   

infile = open("default.config", "r") 

for line in infile:
    if line.strip() in enabledfeatures:
        enabledfeatures[line.strip()] = True



#print enabledfeatures

#Imprimir las variables puestas
#os.system('heroku config')

for  k, v in enabledfeatures.iteritems():
    if v == True:
        #print "heroku config:set "+featureVars[k]+"=TRUE"
        os.system("heroku config:set "+featureVars[k]+"=TRUE")
    else:
        #print "heroku config:set "+featureVars[k]+"=FALSE"
        os.system("heroku config:set "+featureVars[k]+"=FALSE")

#os.system("heroku config:set REPORTEMETRICAS=TRUE")
#os.system('heroku config:set REPORTEMETRICAS=FALSE')
