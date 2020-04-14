require 'calabash-android/calabash_steps'
require 'calabash-android/operations'

Then /^I wait (\d+) seconds$/ do |arg1|
	sleep(arg1.to_i) 
end

Then /^I escribir$/ do 
  keyboard_enter_text("hola",options={})
  print "GH"
end

Then /^I enter now "([^\"]*)" as "([^\"]*)"$/ do |text, field|
  enter_text("* id:'nombreUsuario'", text)
  press_user_action_button
end

Then /^I check if exists "([^\"]*)"$/ do |element|
	wait_for_elements_exist("* id:'#{element}'")
end

Then /^I press "([^\"]*)" while exists$/ do |element|
  while element_exists("* id:'#{element}'")
    tap_when_element_exists("* id:'#{element}'")
  end
end
