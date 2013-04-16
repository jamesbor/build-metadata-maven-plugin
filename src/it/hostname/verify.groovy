File file = new File( basedir, "build.log" );
assert file.exists();

String text = file.getText("utf-8");

assert text =~ /\[DEBUG\] define property build\.hostname = "(.*)"/

assert text.contains("[echo] build.hostname=")
return true;
