<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Voitures
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <!-- Le  type de methode http qui sera appelï¿½ lors de action submit du formulaire -->
                        <!-- est dï¿½crit an l'attribut "method" de la balise forme -->
                        <!-- action indique ï¿½ quel "cible" sera envoyr la requï¿½te, ici notre Servlet qui sera bind sur -->
                        <!-- /vehicles/create -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                            <div class="form-group">
                                    <label for="id" class="col-sm-2 control-label">Id</label>

									<!-- Pour rï¿½upï¿½rer la valeur rentrï¿½e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est spï¿½cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="id" name="id" min="1" placeholder="Id" required>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-sm-2 control-label">Modifications : </label></div> 
                                <div class="form-group"><p STYLE="padding:0 0 0 100px;">(Remettez la même valeur si vous ne souhaitez pas modifier un des paramètres)</p></div>
                                <div class="form-group">
                                    <label for="manufacturer" class="col-sm-2 control-label">Marque</label>

									<!-- Pour rï¿½upï¿½rer la valeur rentrï¿½e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est spï¿½cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="seats" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm-10">
                                        <input type="number" class="form-control" id="seats" name="seats" min="2" max="9" placeholder="Nombre de places" required>
                                    </div>
                                </div>
                                <!--
                                <div class="form-group">
                                    <label for="owner" class="col-sm-2 control-label">PropriÃ©taire</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="owner" name="owner">
                                            <option value="1">John Doe</option>
                                            <option value="2">Jane Doe</option>
                                        </select>
                                    </div>
                                </div>
                                -->
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" id="EditVehicles" href="/cars">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
