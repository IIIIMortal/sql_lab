import functools
import os
import subprocess
import sys

import qt5_applications


fspath = getattr(os, 'fspath', str)


def run(application_name, environment=os.environ):
    modified_environment = qt5_applications.create_environment(reference=environment)
    application_path = qt5_applications.application_path(application_name)

    completed_process = subprocess.run(
        [
            fspath(application_path),
            *sys.argv[1:],
        ],
        env=modified_environment,
    )

    sys.exit(completed_process.returncode)


# designer = functools.partial(run, application_name='designer')

# ----  start of generated wrapper entry points

assistant = functools.partial(run, application_name='assistant')
canbusutil = functools.partial(run, application_name='canbusutil')
designer = functools.partial(run, application_name='designer')
dumpcpp = functools.partial(run, application_name='dumpcpp')
dumpdoc = functools.partial(run, application_name='dumpdoc')
lconvert = functools.partial(run, application_name='lconvert')
linguist = functools.partial(run, application_name='linguist')
lprodump = functools.partial(run, application_name='lprodump')
lrelease_pro = functools.partial(run, application_name='lrelease-pro')
lrelease = functools.partial(run, application_name='lrelease')
lupdate_pro = functools.partial(run, application_name='lupdate-pro')
lupdate = functools.partial(run, application_name='lupdate')
pixeltool = functools.partial(run, application_name='pixeltool')
qdbus = functools.partial(run, application_name='qdbus')
qdbuscpp2xml = functools.partial(run, application_name='qdbuscpp2xml')
qdbusviewer = functools.partial(run, application_name='qdbusviewer')
qdbusxml2cpp = functools.partial(run, application_name='qdbusxml2cpp')
qdistancefieldgenerator = functools.partial(run, application_name='qdistancefieldgenerator')
qdoc = functools.partial(run, application_name='qdoc')
qgltf = functools.partial(run, application_name='qgltf')
qhelpgenerator = functools.partial(run, application_name='qhelpgenerator')
qlalr = functools.partial(run, application_name='qlalr')
qml = functools.partial(run, application_name='qml')
qmlcachegen = functools.partial(run, application_name='qmlcachegen')
qmleasing = functools.partial(run, application_name='qmleasing')
qmlformat = functools.partial(run, application_name='qmlformat')
qmlimportscanner = functools.partial(run, application_name='qmlimportscanner')
qmllint = functools.partial(run, application_name='qmllint')
qmlmin = functools.partial(run, application_name='qmlmin')
qmlplugindump = functools.partial(run, application_name='qmlplugindump')
qmlpreview = functools.partial(run, application_name='qmlpreview')
qmlprofiler = functools.partial(run, application_name='qmlprofiler')
qmlscene = functools.partial(run, application_name='qmlscene')
qmltestrunner = functools.partial(run, application_name='qmltestrunner')
qmltyperegistrar = functools.partial(run, application_name='qmltyperegistrar')
qscxmlc = functools.partial(run, application_name='qscxmlc')
qtattributionsscanner = functools.partial(run, application_name='qtattributionsscanner')
qtdiag = functools.partial(run, application_name='qtdiag')
qtpaths = functools.partial(run, application_name='qtpaths')
qtplugininfo = functools.partial(run, application_name='qtplugininfo')
qvkgen = functools.partial(run, application_name='qvkgen')
repc = functools.partial(run, application_name='repc')
testcon = functools.partial(run, application_name='testcon')
uic = functools.partial(run, application_name='uic')
xmlpatterns = functools.partial(run, application_name='xmlpatterns')
xmlpatternsvalidator = functools.partial(run, application_name='xmlpatternsvalidator')

# ----  end of generated wrapper entry points

